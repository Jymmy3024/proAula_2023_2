/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Cliente;
import java.util.List;

/**
 *
 * @author York Severiche
 */
public interface IClienteServicio {
    
    public List<Cliente> listarClientes();
    
    public void guardarCliente (Cliente cliente);
    
    public void eliminar (Cliente cliente);
    
    public Cliente buscar (Cliente cliente);
    
    public Cliente loginCliente (String correo, String password);
}
