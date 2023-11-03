
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Producto;
import co.unicolombo.edu.models.ProductoGlobal;
import org.springframework.stereotype.Service;

/**
 *
 * @author CDOG
 */


public interface ProductoServicio {         
    
    public void guardarProducto(Producto producto);

    public Producto validar(Producto producto) throws Exception;
    
    public Producto getById(Integer id);
    
    public boolean existsById(Integer id);
    
    public boolean exitsProductoGlobalInTienda(ProductoGlobal productoGlobal, Integer nitTienda);
    
}
