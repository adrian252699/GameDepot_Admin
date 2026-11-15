package com.example.GameDepot_Admin.controllers.restControllers;

import com.example.GameDepot_Admin.dto.videojuego.ActualizarVideojuegoDTO;
import com.example.GameDepot_Admin.dto.videojuego.GuardarVideojuegoDTO;
import com.example.GameDepot_Admin.dto.videojuego.MostrarVideojuegoDTO;
import com.example.GameDepot_Admin.service.ClaveService;
import com.example.GameDepot_Admin.service.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/videojuego")
public class VideojuegoController {

    /*Un cliente solo tendra acceso visual a los videojuegos,
    * no puede crear o modificar videojuegos*/

    /*Aun falta agregar filtros de busqueda*/

    @Autowired
    private VideojuegoService videojuegoService;


    /*Este endpoint se utiliza para cargar la pagina principal*/
    @GetMapping()
    public List<MostrarVideojuegoDTO> listarVideojuegosActivos(){
        return videojuegoService.obtenerVideosjuegosActivos();
    }

    /*Este endpoint se utilizara al seleccionar un videojuego y mostrar su informacion*/
    @GetMapping("/{id}")
    public MostrarVideojuegoDTO obtenerVideojuegoPorId(@PathVariable Long id){
        return videojuegoService.obtenerPorId(id);
    }


    @PostMapping()
    public GuardarVideojuegoDTO crearVideojuego(@RequestBody GuardarVideojuegoDTO videojuego){
        return videojuegoService.guardarVideojuego(videojuego);
    }


    @PatchMapping("/{id}")
    public ActualizarVideojuegoDTO actualizarVideojuego(@PathVariable Long id, @RequestBody ActualizarVideojuegoDTO videojuego){
        return videojuegoService.actualizarVideojuego(id, videojuego);
    }

}
