/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Usuario;
import co.unicolombo.edu.repositories.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author York Severiche
 */
@Service
public class UsuarioServicioImp implements IUsuarioServicio{
    
    @Autowired
    UsuarioRepository usuarioRepo;
    
    @Transactional(readOnly = true)
    @Override
    public List<Usuario> listarUsuarios(){
        return (List<Usuario>) usuarioRepo.findAll();
    }
    
    @Transactional
    @Override
    public void guardar(Usuario user) {
        usuarioRepo.save(user);
    }

    @Transactional
    @Override
    public void eliminar(Usuario user) {
        usuarioRepo.delete(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario buscar(Usuario user) {
        String cedula = user.getCedula();
    return usuarioRepo.findById(cedula).orElse(null);
    }


}
