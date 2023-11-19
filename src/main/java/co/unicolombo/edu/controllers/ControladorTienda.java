/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.controllers;

import co.unicolombo.edu.models.Tienda;
import co.unicolombo.edu.services.ITiendaServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author York Severiche
 */
@Controller
@Slf4j
public class ControladorTienda {
    
    @Autowired
    ITiendaServicio tiendaServicio;
    
    @GetMapping("/agregar")
    public String agregar (Tienda tienda){
        return "tienda";
    }
    
    @PostMapping("/guardar")
        public String guardar(Tienda tienda) throws Exception {
        tiendaServicio.agregarTienda(tienda);
        return "redirect:/";
    }
}
