/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.ProductoGlobal;
import java.util.List;

/**
 *
 * @author CDOG
 */
public interface ProductoGlobalServicio {
    
    public List<ProductoGlobal> listAll(String palabraClave);
    
    public ProductoGlobal getByCodigo(Integer codigo); 
    
}
