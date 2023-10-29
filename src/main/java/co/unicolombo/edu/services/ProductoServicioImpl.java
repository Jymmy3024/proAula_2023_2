/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Producto;
import co.unicolombo.edu.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CDOG
 */
@Service
public class ProductoServicioImpl implements ProductoServicio{
    
    @Autowired
    private ProductoRepository repositorio;
    
    @Override
    public void guardarProducto(Producto producto) {
        
    }
    
}
