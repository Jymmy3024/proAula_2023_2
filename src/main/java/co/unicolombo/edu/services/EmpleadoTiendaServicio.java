/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.EmpleadoTienda;
import co.unicolombo.edu.repositories.EmpleadoTiendaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author York Severiche
 */
@Service
public class EmpleadoTiendaServicio implements IEmpleadoTiendaServicio{
    
    @Autowired
    EmpleadoTiendaRepository empladoRepo;
    
    @Transactional(readOnly = true)
    @Override
    public List<EmpleadoTienda> listarEmpleados(){
        return (List<EmpleadoTienda>) empladoRepo.findAll();
    }
    
    @Transactional
    @Override
    public void guardar(EmpleadoTienda empleado) {
        empladoRepo.save(empleado);
    }

    @Transactional
    @Override
    public void eliminar(EmpleadoTienda empleado) {
        empladoRepo.delete(empleado);
    }

    @Transactional(readOnly = true)
    @Override
    public EmpleadoTienda buscar(EmpleadoTienda empleado) {
        String cedulaString = empleado.getUsuario().getCedula();
        int cedula = Integer.parseInt(cedulaString);
        
        return empladoRepo.findById(cedula).orElse(null);
    }

}
