/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.util;

import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author CDOG
 */

public class MensajeController {
    
    public ModelAndView mostrarException(Exception e){
        
        Mensaje m = new Mensaje(e);        
        return new ModelAndView("mensajes/error")
                .addObject("mensaje", m);
    }
    
    
    public ModelAndView mensajeExitoso(Mensaje mensajeExito){               
        
        return new ModelAndView("mensajes/exito")
                .addObject("mensaje", mensajeExito);
    }
    
    public ModelAndView mensajeAdvertencia(Mensaje mensajeAdvertencia){
        return new ModelAndView("mensajes/advertencia")
                .addObject("mensaje", mensajeAdvertencia);
    }
}
