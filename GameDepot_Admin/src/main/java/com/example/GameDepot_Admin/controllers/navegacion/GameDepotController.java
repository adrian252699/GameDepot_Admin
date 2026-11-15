package com.example.GameDepot_Admin.controllers.navegacion;

import com.example.GameDepot_Admin.dto.admin.EstadisticasDTO;
import com.example.GameDepot_Admin.dto.admin.OrdenDTO;
import com.example.GameDepot_Admin.dto.admin.ProductoDTO;
import com.example.GameDepot_Admin.dto.admin.UsuarioDTO;
import com.example.GameDepot_Admin.dto.usuario.MostrarUsuarioDTO;
import com.example.GameDepot_Admin.dto.videojuego.MostrarVideojuegoDTO;
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

        MostrarUsuarioDTO admin = usuarioService.obtenerPorEmail(principal.getName());

        List<OrdenDTO> pedidosRecientes = ordenService.getOrdenesEntregadasDelMes();

        List<MostrarUsuarioDTO> compradores = ordenService.getUsuariosPorOrdenEntregada();
        Map<Long, String> compradoresPorId = new HashMap<>();

        for (var comprador : compradores) {
            compradoresPorId.put(comprador.getId(), comprador.getNombre());
        }

        model.addAttribute("pedidosRecientes",pedidosRecientes);
        model.addAttribute("compradoresPorId", compradoresPorId);

        model.addAttribute("stats", stats);
        model.addAttribute("nombreAdmin", admin.getNombre());
        model.addAttribute("emailAdmin", admin.getEmail());

        return "dashboard";
    }

    @GetMapping("/catalogo")
    public String productos(Model model) {
        List<MostrarVideojuegoDTO> listaVideojuegos = videojuegoService.mostrarVideojuego();

        model.addAttribute("videojuegos", listaVideojuegos);

        return  "catalogo";
    }

    @GetMapping("/catalogo/nuevo")
    public String productoNuevo() {
        return  "catalogo/nuevo";
    }

    @GetMapping("/catalogo/editar/{id}")
    public String editarProducto(@PathVariable("id")Long id, Model model) {
        MostrarVideojuegoDTO producto = videojuegoService.obtenerPorId(id);
        model.addAttribute("producto", producto);
        return  "catalogo/editar";
    }

    @GetMapping("/pedidos")
    public String pedidos(Model model) {
        List<OrdenDTO> pedidosRecientes = ordenService.getOrdenes();

        List<MostrarUsuarioDTO> compradores = ordenService.getUsuariosPorOrden();
        Map<Long, String> compradoresPorId = new HashMap<>();

        for (var comprador : compradores) {
            compradoresPorId.put(comprador.getId(), comprador.getNombre());
        }

        model.addAttribute("pedidosRecientes",pedidosRecientes);
        model.addAttribute("compradoresPorId", compradoresPorId);

        return  "pedidos";
    }

    @GetMapping("/pedidos/gestionar/{id}")
    public String gestionarPedidos(@PathVariable("id") Long id, Model model) {
        OrdenDTO pedido = ordenService.obtenerOrdenPorId(id);

        model.addAttribute("pedido", pedido);

        return  "pedidos/gestionar";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {

        List<MostrarUsuarioDTO> usuarios = usuarioService.mostrarUsuarios();

        model.addAttribute("usuarios", usuarios);

        return  "usuarios";
    }

    @GetMapping("/usuarios/gestionar/{id}")
    public String gestionarUsuario(@PathVariable("id") Long id, Model model) {
        MostrarUsuarioDTO usuario = usuarioService.obtenerPorId(id);

        model.addAttribute("usuario", usuario);

        return  "usuarios/gestionar";
    }


}
