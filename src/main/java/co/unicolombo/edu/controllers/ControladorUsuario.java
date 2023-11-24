
package co.unicolombo.edu.controllers;

import co.unicolombo.edu.models.Cliente;
import org.springframework.ui.Model;
import co.unicolombo.edu.services.IClienteServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 *
 * @author York Severiche
 */
@Controller
@Slf4j
public class ControladorUsuario {
    
    @Autowired
    IClienteServicio clienteServicio;
  
    
    @GetMapping("/agregarCliente")
    public String agregarCliente(Model model) {
        Cliente cliente = new Cliente();
        
        model.addAttribute("cliente", cliente);
        
        
        return "registrarCliente"; // Retorna el nombre de tu plantilla
    }
    
    @PostMapping("/guardarCliente") //@Validated para que haga las validaciones 
    public String guardarCliente(@Validated Cliente cliente, BindingResult bindingResult, Model modelo, RedirectAttributes redirectAttributes) { 
        
        try{
        // Validamos los campos del formulario
        if (bindingResult.hasErrors()) {
            modelo.addAttribute("cliente", cliente);
            //Si hay errores te devuelve al formulario
            return "registrarCliente";
        }

        // Guardamos el usuario en la base de datos
        clienteServicio.guardarCliente(cliente);
        
        redirectAttributes.addFlashAttribute("success", cliente.getNombre1()+" ¡Registro exitoso!\nInicie sesión.");
        
        // Redirigimos a la página de login
        return "redirect:/clienteLogin";
        }catch(Exception e){
            System.out.println(e.getCause());
            modelo.addAttribute("exception", e); //Esto es por si hay un error de ejecucion
            //Si hay errores te devuelve al formulario
            return "registrarCliente";
        }
                
    }

}
