package com.example.GameDepot_Admin.controllers.navegacion;

import com.example.GameDepot_Admin.dtos.EstadisticasDTO;
import com.example.GameDepot_Admin.dtos.ProductoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        producto.setNombre("GTA VI");
        model.addAttribute("producto", producto);
        return  "productos/editar";
    }

    @GetMapping("/pedidos")
    public String pedidos(Model model) {
        return  "pedidos";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        return  "usuarios";
    }
}
