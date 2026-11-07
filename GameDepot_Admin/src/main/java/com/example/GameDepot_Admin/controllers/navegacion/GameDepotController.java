package com.example.GameDepot_Admin.controllers.navegacion;

import com.example.GameDepot_Admin.dtos.EstadisticasDTO;
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
        return  "productos/nuevo";
    }
}
