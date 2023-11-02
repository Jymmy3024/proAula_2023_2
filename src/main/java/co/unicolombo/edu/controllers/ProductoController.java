
package co.unicolombo.edu.controllers;

import co.unicolombo.edu.models.Producto;
import co.unicolombo.edu.models.ProductoGlobal;
import co.unicolombo.edu.models.Tienda;
import co.unicolombo.edu.services.ProductoGlobalServicio;
import co.unicolombo.edu.services.ProductoServicio;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author CDOG
 */
@Controller
public class ProductoController {
    
    @Autowired
    private ProductoGlobalServicio pgServicio;//REPOSITORIO DEL PRODUCTO GLOBAL
    
    @Autowired
    private ProductoServicio pServicio;//REPOSITORIO DEL PRODUCTO DE LA TIENDA
    
     
    @GetMapping("productos/agregar")//@param es para obtener el parametro de busqueda "?busqueda="
    public String showFormAddProducts(Model model, @Param("busqueda") String busqueda){        
        model.addAttribute("busqueda", busqueda);
        List<ProductoGlobal> resultados = pgServicio.listAll(busqueda);
        model.addAttribute("productos", resultados);        
        return "producto/agregar_producto";
    }
    
    @PostMapping("productos/registrar/")
    public String showProductRegistrationForm(@RequestParam("codProdGlobal") Integer codigoPg, Model modelo, HttpSession sesion){
        
        //le pasamos el codigo del productoGlobal
        //modelo.addAttribute("codigoProdGlobal", codigoPg);        
        modelo.addAttribute("productoGlobal", pgServicio.getByCodigo(codigoPg));
        
        //le pasamos la tienda
        /*************LA DEBEMOS OBTENER POR MEDIO DEL EMPLEADO EN SESION**/
        sesion.setAttribute("tienda", new Tienda(888,"Tiendas Ara el mejor","Tiendas ARA","","Supermercado",null));
        
        
        Tienda t = (Tienda)sesion.getAttribute("tienda");
        modelo.addAttribute("tienda", t);
        
        Producto p = new Producto();
        modelo.addAttribute("producto", p);
        
        return "producto/registrar_producto";
    }
    
    @PostMapping("productos/agregar")
    public String guardarProducto(Producto producto, ProductoGlobal productoGlobal, HttpSession sesion){      
        //System.out.println(codigo);
        System.out.println(producto);
        System.out.println(productoGlobal);
        productoGlobal = pgServicio.getByCodigo(productoGlobal.getCodigo());
        producto.setProductoGlobal(productoGlobal);
        producto.setTienda((Tienda) sesion.getAttribute("tienda"));
        System.out.println(producto);
        
        //guardamos el producto
        pServicio.guardarProducto(producto);
        return "redirect:/productos/agregar";
    }

}
