/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.EmpleadoTienda;
import java.util.List;

/**
 *
 * @author York Severiche
 */
public interface IEmpleadoTiendaServicio {
    
    public List<EmpleadoTienda> listarEmpleados();
    
    public void guardar (EmpleadoTienda user);
    
    public void eliminar (EmpleadoTienda user);
    
    public EmpleadoTienda buscar (EmpleadoTienda user);
}
