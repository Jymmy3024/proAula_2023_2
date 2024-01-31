/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author CDOG
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Mensaje extends Exception {

    //Esta clase sera para mostrar mensajes de error y/o mensajes    
    String titulo;

    String mensaje;

    public Mensaje(Exception e) {
        this.titulo = "Error";
        this.mensaje = e.getMessage();
    }

}
