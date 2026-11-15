package com.example.GameDepot_Admin.config;

import com.example.GameDepot_Admin.exception.JwtAccessDeniedHandler;
import com.example.GameDepot_Admin.exception.JwtAuthenticationEntryPoint;
import com.example.GameDepot_Admin.repository.UsuarioRepository;
import com.example.GameDepot_Admin.service.jwt.JwtAuthenticationFilter;
import io.jsonwebtoken.security.MacAlgorithm;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Base64;

@Configuration
public class SecurityConfig{

    private final JwtAuthenticationFilter jwtFilter;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter,
                          JwtAuthenticationEntryPoint authenticationEntryPoint,
                          JwtAccessDeniedHandler accessDeniedHandler) {
        this.jwtFilter = jwtFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService adminUserDetailsService(UsuarioRepository repository) {
        return email -> {
            var usuario = repository.findByEmail(email)
                    .filter(u -> "ADMIN".equals(u.getRol().name()))
                    .orElseThrow(() -> new UsernameNotFoundException("Cuenta no encontrada"));

            return User.withUsername(usuario.getEmail())
                    .password(usuario.getPassword())
                    .disabled(!usuario.getActivo())
                    .roles("ADMIN")
                    .build();
        };
    }

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var rutas = PathPatternRequestMatcher.withDefaults();
        var rutasApi = rutas.matcher("/api/**");
        var imagenes = rutas.matcher("/uploads/videojuegos/**");
        var crearVideojuego = rutas.matcher(HttpMethod.POST, "/api/videojuego");
        var editarVideojuego = rutas.matcher(HttpMethod.PATCH, "/api/videojuego/{id}");
        return http
                .securityMatcher(request ->
                        (rutasApi.matches(request) || imagenes.matches(request))
                                && (!(crearVideojuego.matches(request) || editarVideojuego.matches(request))
                                || request.getHeader(HttpHeaders.AUTHORIZATION) != null)
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .authorizeHttpRequests(auth -> auth
                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                        .requestMatchers(HttpMethod.POST,
                                "/api/usuario", "/api/auth",
                                "/api/pagos/mercado-pago/webhook").permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/videojuego/**", "/uploads/videojuegos/**").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/carrito/**", "/api/carrito/items/**").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/api/ordenes").hasRole("CLIENTE")
                        .requestMatchers("/api/ordenes/**").hasRole("CLIENTE")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain dashboardSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth -> auth
                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/", "/login", "/styles/**", "/favicon.ico").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .anyRequest().hasRole("ADMIN"))
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("contrasena")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error"))
                .build();
    }

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilterRegistration() {
        FilterRegistrationBean<JwtAuthenticationFilter> registration =
                new FilterRegistrationBean<>(jwtFilter);
        registration.setEnabled(false);
        return registration;
    }
}
