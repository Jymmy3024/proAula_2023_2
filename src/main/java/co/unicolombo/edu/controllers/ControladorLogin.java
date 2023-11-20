/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.controllers;

import co.unicolombo.edu.models.Cliente;
import co.unicolombo.edu.models.Usuario;
import co.unicolombo.edu.services.IClienteServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author York Severiche
 */
@Controller
@Slf4j
public class ControladorLogin {

    private final IClienteServicio clienterServicio;

    @Autowired
    public ControladorLogin(IClienteServicio clienterServicio) {
        this.clienterServicio = clienterServicio;
    }
    
    @GetMapping("/clienteLogin") 
    public String login(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/iniciarSesion")
    public String login(@ModelAttribute("usuario") Usuario user, Model modelo, RedirectAttributes redirectAttributes) {
        
        Cliente iniciar = clienterServicio.loginCliente(user.getCorreo(), user.getPassword());
        if (iniciar == null) {
            modelo.addAttribute("mensajeError", "Email o contraseña incorrecta");
            return "login";
        } else {
            
            redirectAttributes.addFlashAttribute("nUser",  iniciar.getNombre1().charAt(0));
            return "redirect:/inicio";
        }
    }
}
