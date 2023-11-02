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

    @Override
    public ProductoGlobal getByCodigo(Integer codigo) {
        return repositorio.findById(codigo).get();
    }

    @Override
    public boolean existsBycodigo(Integer codigo) {
        return repositorio.existsById(codigo);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return false;
    }

    @Override
    public ProductoGlobal validar(ProductoGlobal productoGlobal) throws Exception{
        
        productoGlobal.setNombre(productoGlobal.getNombre().trim());
        productoGlobal.setDescripcion(productoGlobal.getDescripcion().trim());        
        
        if(productoGlobal == null){
            throw new Exception("El producto no puede ser nulo");
        }else if(productoGlobal.getCodigo() > 0 ){
            if(this.existsBycodigo(productoGlobal.getCodigo())){
                throw new Exception("El producto con codigo "+productoGlobal.getCodigo()+" ya existe.");
            }
        }else{
            throw new Exception("Eror en el codigo del producto");
        }
        
        if(productoGlobal.getNombre() == null ){
            throw new Exception("El producto no puede ser nulo");
        }else if(productoGlobal.getNombre().isEmpty() || productoGlobal.getNombre().isEmpty()){
            if(this.existsByNombre(productoGlobal.getNombre())){
                throw new Exception("El producto con nombre "+productoGlobal.getNombre()+" ya existe.");
            }
        }else{
            throw new Exception("Eror en el nombre del producto");
        }
        
        return productoGlobal;
        
    }

    @Override
    public void save(ProductoGlobal productoGlobal) {
       this.repositorio.save(productoGlobal);
    }


}
