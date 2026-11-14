package com.example.GameDepot_Admin.controllers.navegacion;

import com.example.GameDepot_Admin.dtos.EstadisticasDTO;
import com.example.GameDepot_Admin.dtos.PedidoDTO;
import com.example.GameDepot_Admin.dtos.ProductoDTO;
import com.example.GameDepot_Admin.dtos.UsuarioDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class GameDepotController {
    @GetMapping({"/","/dashboard"})
    public String dashboard(Model model) {
        EstadisticasDTO stats = new EstadisticasDTO(45000.00,1205,342,8901);

        model.addAttribute("stats", stats);

        return "dashboard";
    }

    @GetMapping("/catalogo")
    public String productos(Model model) {
        return  "catalogo";
    }

    @GetMapping("/productos/nuevo")
    public String productoNuevo(Model model) {
        ProductoDTO producto = new ProductoDTO();

        model.addAttribute("producto", producto);

        return  "productos/nuevo";
    }

    @GetMapping("/productos/editar")
    public String editarProducto(Model model) {
        ProductoDTO producto = new ProductoDTO();
        producto.setId(20L);
        producto.setNombre("GTA VI");
        producto.setKey("GT-2828-STM");
        producto.setPlataforma("ps5");
        producto.setRegion("latam");
        producto.setPrecio(1800.00);
        producto.setStock(200);
        producto.setCategoria("accion");
        producto.setDescripcion("El mejor juego de la historia");
        model.addAttribute("producto", producto);
        return  "productos/editar";
    }

    @GetMapping("/pedidos")
    public String pedidos(Model model) {
        return  "pedidos";
    }

    @GetMapping("/pedidos/gestionar")
    public String gestionarPedidos(Model model) {
        PedidoDTO pedido = new PedidoDTO();

        pedido.setId(1L);
        pedido.setEstado("procesando");
        pedido.setFecha(LocalDateTime.now());
        pedido.setFolio("GD-2026-80983AA5");
        pedido.setTotal(1500.00);
        pedido.setUsuarioId(1L);
        pedido.setEstadoPago("pendiente");

        model.addAttribute("pedido", pedido);

        return  "pedidos/gestionar";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        return  "usuarios";
    }

    @GetMapping("/usuarios/gestionar")
    public String gestionarUsuario(Model model) {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(1L);
        usuario.setNombre("Adrian");
        usuario.setRol("admin");
        usuario.setPais("Mexico");
        usuario.setEmail("correo@ejemplo.com");
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setActivo(true);

        model.addAttribute("usuario", usuario);

        return  "usuarios/gestionar";
    }


}
