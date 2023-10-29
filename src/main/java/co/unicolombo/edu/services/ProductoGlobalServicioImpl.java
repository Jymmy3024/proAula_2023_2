/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.ProductoGlobal;
import co.unicolombo.edu.repositories.ProductoGlobalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CDOG
 */
@Service
public class ProductoGlobalServicioImpl implements ProductoGlobalServicio{

    @Autowired
    private ProductoGlobalRepository repositorio;
    
    @Override
    public List<ProductoGlobal> listAll(String palabraClave) {
        if(palabraClave != null && !palabraClave.isEmpty()){
            return repositorio.findAll(palabraClave);            
        }
        return repositorio.findAll();
    }
    
}
