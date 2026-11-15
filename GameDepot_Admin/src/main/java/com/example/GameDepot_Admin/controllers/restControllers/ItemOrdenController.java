package com.example.GameDepot_Admin.controllers.restControllers;

import com.example.GameDepot_Admin.service.ClaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/itemOrden")
public class ItemOrdenController {
    @Autowired
    private ClaveService claveService;

    @GetMapping("/{itemId}")
    public List<String> getClaves(@PathVariable Long itemId){
        return claveService.obtenerClaveByOrdenId(itemId);
    }

    @PutMapping("/item/{itemId}/claves/{claveId}")
    public ResponseEntity<Void> asignarClave(@PathVariable("itemId") Long itemId, @PathVariable("claveId") Long claveId) {
        claveService.asignarClave(itemId, claveId);
        return ResponseEntity.noContent().build();
    }
}
