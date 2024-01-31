/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Usuario;
import java.util.List;

/**
 *
 * @author York Severiche
 */
public interface IUsuarioServicio {
    
    public List<Usuario> listarUsuarios();
    
    public void guardar (Usuario user);
    
    public void eliminar (Usuario user);
    
    public Usuario buscar (Usuario user);
}
