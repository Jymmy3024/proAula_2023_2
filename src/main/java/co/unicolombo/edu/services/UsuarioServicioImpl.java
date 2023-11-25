/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Usuario;
import co.unicolombo.edu.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author CDOG
 */
@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    UsuarioRepository repositorio;

    @Transactional(readOnly = true) //solo va a leer datos
    @Override
    public boolean existeByCedula(String cedula) {
        return repositorio.existsById(cedula);
    }

    @Transactional(readOnly = true) //solo va a leer datos
    @Override
    public Usuario buscarByCedula(String cedula) {
        return this.repositorio.findById(cedula).orElse(null);
    }

    @Override
    public void guardar(Usuario usuario) throws Exception{
        //if (!this.existeByCedula(usuario.getCedula())) {
            this.repositorio.save(usuario);
        /*}else{
            throw new Exception("El usuario con "+usuario.getCedula()+" ya existe.");
        }*/
    }

    @Override
    public boolean existeByCorreo(String correo) {
        return this.repositorio.existsByCorreo(correo);
    }

}
