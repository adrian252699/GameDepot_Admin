package com.example.GameDepot_Admin.controllers.navegacion;

import com.example.GameDepot_Admin.dto.admin.EstadisticasDTO;
import com.example.GameDepot_Admin.dto.admin.OrdenDTO;
import com.example.GameDepot_Admin.dto.admin.ProductoDTO;
import com.example.GameDepot_Admin.dto.admin.UsuarioDTO;
import com.example.GameDepot_Admin.dto.usuario.MostrarUsuarioDTO;
import com.example.GameDepot_Admin.dto.videojuego.*;
import com.example.GameDepot_Admin.service.EstadisticasService;
import com.example.GameDepot_Admin.service.OrdenService;
import com.example.GameDepot_Admin.service.UsuarioService;
import com.example.GameDepot_Admin.service.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GameDepotController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EstadisticasService estadisticasService;

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private VideojuegoService videojuegoService;

    @GetMapping({"/","/login"})
    public String index(){
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        EstadisticasDTO stats = estadisticasService.getEstadisticasDTO();

        List<OrdenDTO> pedidosRecientes = ordenService.getOrdenesEntregadasDelMes();

        List<MostrarUsuarioDTO> compradores = ordenService.getUsuariosPorOrdenEntregada();
        Map<Long, String> compradoresPorId = new HashMap<>();

        for (var comprador : compradores) {
            compradoresPorId.put(comprador.getId(), comprador.getNombre());
        }

        model.addAttribute("pedidosRecientes",pedidosRecientes);
        model.addAttribute("compradoresPorId", compradoresPorId);
        model.addAttribute("stats", stats);

        /*Mostrar datos del admin*/
        MostrarUsuarioDTO admin = usuarioService.obtenerPorEmail(principal.getName());
        model.addAttribute("nombreAdmin", admin.getNombre());
        model.addAttribute("emailAdmin", admin.getEmail());

        return "dashboard";
    }

    @GetMapping("/catalogo")
    public String productos(Model model,Principal principal) {
        List<MostrarVideojuegoDTO> listaVideojuegos = videojuegoService.mostrarVideojuego();

        /*Mostrar datos del admin*/
        MostrarUsuarioDTO admin = usuarioService.obtenerPorEmail(principal.getName());
        model.addAttribute("nombreAdmin", admin.getNombre());
        model.addAttribute("emailAdmin", admin.getEmail());

        model.addAttribute("videojuegos", listaVideojuegos);

        return  "catalogo";
    }

    @GetMapping("/catalogo/nuevo")
    public String productoNuevo(Model model,Principal principal) {
        GuardarVideojuegoDTO videojuego = new GuardarVideojuegoDTO();

        List<CategoriaDTO> listaCategorias = videojuegoService.obtenerCategorias();
        List<PlataformaDTO> listaPlataformas = videojuegoService.obtenerPlataformas();
        List<RegionDTO> listaRegiones = videojuegoService.obtenerRegiones();

        /*Mostrar datos del admin*/
        MostrarUsuarioDTO admin = usuarioService.obtenerPorEmail(principal.getName());
        model.addAttribute("nombreAdmin", admin.getNombre());
        model.addAttribute("emailAdmin", admin.getEmail());

        model.addAttribute("regiones", listaRegiones);
        model.addAttribute("plataformas", listaPlataformas);
        model.addAttribute("categorias", listaCategorias);
        model.addAttribute("videojuego", videojuego);
        return  "catalogo/nuevo";
    }

    @GetMapping("/catalogo/editar/{id}")
    public String editarProducto(@PathVariable("id")Long id, Model model,Principal principal) {
        MostrarVideojuegoDTO producto = videojuegoService.obtenerPorId(id);
        List<CategoriaDTO> listaCategorias = videojuegoService.obtenerCategorias();
        List<PlataformaDTO> listaPlataformas = videojuegoService.obtenerPlataformas();
        List<RegionDTO> listaRegiones = videojuegoService.obtenerRegiones();

        model.addAttribute("regiones", listaRegiones);
        model.addAttribute("plataformas", listaPlataformas);
        model.addAttribute("categorias", listaCategorias);
        model.addAttribute("videojuego", producto);

        /*Mostrar datos del admin*/
        MostrarUsuarioDTO admin = usuarioService.obtenerPorEmail(principal.getName());
        model.addAttribute("nombreAdmin", admin.getNombre());
        model.addAttribute("emailAdmin", admin.getEmail());

        return  "catalogo/editar";
    }

    @GetMapping("/pedidos")
    public String pedidos(Model model,Principal principal) {
        List<OrdenDTO> pedidosRecientes = ordenService.getOrdenes();

        List<MostrarUsuarioDTO> compradores = ordenService.getUsuariosPorOrden();
        Map<Long, String> compradoresPorId = new HashMap<>();

        for (var comprador : compradores) {
            compradoresPorId.put(comprador.getId(), comprador.getNombre());
        }

        model.addAttribute("pedidosRecientes",pedidosRecientes);
        model.addAttribute("compradoresPorId", compradoresPorId);

        /*Mostrar datos del admin*/
        MostrarUsuarioDTO admin = usuarioService.obtenerPorEmail(principal.getName());
        model.addAttribute("nombreAdmin", admin.getNombre());
        model.addAttribute("emailAdmin", admin.getEmail());

        return  "pedidos";
    }

    @GetMapping("/pedidos/gestionar/{id}")
    public String gestionarPedidos(@PathVariable("id") Long id, Model model,Principal principal) {
        OrdenDTO pedido = ordenService.obtenerOrdenPorId(id);

        model.addAttribute("itemsPedido", ordenService.listarItemsDelPedido(id));
        model.addAttribute("pedido", pedido);

        /*Mostrar datos del admin*/
        MostrarUsuarioDTO admin = usuarioService.obtenerPorEmail(principal.getName());
        model.addAttribute("nombreAdmin", admin.getNombre());
        model.addAttribute("emailAdmin", admin.getEmail());

        return  "pedidos/gestionar";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model,Principal principal) {

        List<MostrarUsuarioDTO> usuarios = usuarioService.mostrarUsuarios();

        model.addAttribute("usuarios", usuarios);

        /*Mostrar datos del admin*/
        MostrarUsuarioDTO admin = usuarioService.obtenerPorEmail(principal.getName());
        model.addAttribute("nombreAdmin", admin.getNombre());
        model.addAttribute("emailAdmin", admin.getEmail());

        return  "usuarios";
    }

    @GetMapping("/usuarios/gestionar/{id}")
    public String gestionarUsuario(@PathVariable("id") Long id, Model model,Principal principal) {
        MostrarUsuarioDTO usuario = usuarioService.obtenerPorId(id);

        model.addAttribute("usuario", usuario);

        /*Mostrar datos del admin*/
        MostrarUsuarioDTO admin = usuarioService.obtenerPorEmail(principal.getName());
        model.addAttribute("nombreAdmin", admin.getNombre());
        model.addAttribute("emailAdmin", admin.getEmail());

        return  "usuarios/gestionar";
    }


}
