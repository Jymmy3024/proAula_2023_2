
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Producto;
import co.unicolombo.edu.models.ProductoGlobal;
import co.unicolombo.edu.models.Tienda;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
        
    public Page<Producto> listAllByTienda(Tienda tienda, Pageable pageable) throws Exception;
    
    public List<Producto> searchInTienda (Integer tienda, String busqueda);
}
