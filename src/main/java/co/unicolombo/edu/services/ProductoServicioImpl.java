/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Producto;
import co.unicolombo.edu.models.ProductoGlobal;
import co.unicolombo.edu.models.Tienda;
import co.unicolombo.edu.repositories.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        
        if(producto != null){
            repositorio.save(producto);
        }
        
    }

    @Override
    public Producto validar(Producto producto) throws Exception{

        producto.setEstado(producto.getEstado().trim());
        
        
        if (producto == null) {
            throw new Exception("El producto no puede ser nulo");
        } else if (exitsProductoGlobalInTienda(producto.getProductoGlobal(),
                producto.getTienda().getNit())) {
            throw new Exception("El producto ya existe en la tienda");
        }             
        return producto;
    }

    @Override
    public Producto getById(Integer id) {
        return this.repositorio.findById(id).get();
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repositorio.existsById(id);
    }

    @Override
    public boolean exitsProductoGlobalInTienda(ProductoGlobal productoGlobal, Integer nitTienda) {        
        List<Producto> subProductos = productoGlobal.getSubProductos();
        if(subProductos == null || subProductos.isEmpty()){
            return false;
        }
        for(Producto p: subProductos){
            if(p.getTienda().getNit() == nitTienda){
                return true;
            }
        }        
        return false;
    }
    
    @Override
    public Page<Producto> listAllByTienda(Tienda tienda, Pageable pageable) throws Exception{
        Page<Producto> productos = repositorio.findAllByTienda(tienda, pageable);
        if(productos == null || productos.isEmpty()){
            throw new Exception("La tienda: " + tienda.getNombre() + ", no ha registrado productos aun.");
        }else{
            return productos;
        }
    }
    
}
