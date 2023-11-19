/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Usuario;

/**
 *
 * @author CDOG
 */
public interface UsuarioServicio {
    
    
    public boolean existeByCedula(String cedula);
    
    public Usuario buscarByCedula(String cedula);
    
    public void guardar(Usuario usuario) throws Exception;
    
}
