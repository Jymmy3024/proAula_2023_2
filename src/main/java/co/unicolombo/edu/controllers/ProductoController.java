package co.unicolombo.edu.controllers;

import co.unicolombo.edu.models.Producto;
import co.unicolombo.edu.models.ProductoGlobal;
import co.unicolombo.edu.models.Tienda;
import co.unicolombo.edu.services.ITiendaServicio;
import co.unicolombo.edu.services.ProductoGlobalServicio;
import co.unicolombo.edu.services.ProductoServicio;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    
    @Autowired
    private ITiendaServicio tServicio;  //SERVICIO DE LAS TIENDAS    

    @GetMapping("productos/agregar")//@param es para obtener el parametro de busqueda "?busqueda="
    public ModelAndView showFormAddProducts(@Param("busqueda") String busqueda) {
        List<ProductoGlobal> resultados = pgServicio.listAll(busqueda);
        return new ModelAndView("producto/agregar_producto").addObject("busqueda", busqueda)
                .addObject("productos", resultados);
    }

    
    @PostMapping("productos/registrar/")
    public ModelAndView showProductRegistrationForm(@RequestParam("codProdGlobal") Integer codigoPg, Model modelo, HttpSession sesion) {
        //le pasamos la tienda
        /**
         * ***********LA DEBEMOS OBTENER POR MEDIO DEL EMPLEADO EN SESION*
         */
        Tienda t = tServicio.obtenerPorNit(888);
        Producto p = new Producto();
        p.setProductoGlobal(pgServicio.getByCodigo(codigoPg));
        p.setTienda(t);
                
        return new ModelAndView("producto/registrar_producto")
                //.addObject("productoGlobal", pgServicio.getByCodigo(codigoPg))
                //.addObject("tienda", t)
                .addObject("producto", p);
    }

    @PostMapping("productos/agregar")
    public ModelAndView guardarProducto(@Validated Producto producto, BindingResult result, Model modelo, HttpSession sesion){
        if(result.hasErrors()){            
            System.out.println(result.getFieldError());
            System.out.println("\n\n\n----------------------------------TIENE ERRORES\n\n\n");
                return new ModelAndView("producto/registrar_producto")
                .addObject("producto", producto);
        }
        
        System.out.println("AQUIIII");        
        try {
            /*            System.out.println(producto);
            System.out.println(productoGlobal);
            productoGlobal = pgServicio.getByCodigo(productoGlobal.getCodigo());
            producto.setProductoGlobal(productoGlobal);
            producto.setTienda((Tienda) sesion.getAttribute("tienda"));
            System.out.println(producto);*/

            //guardamos el producto
            producto = pServicio.validar(producto);
            pServicio.guardarProducto(producto);
            return this.showFormAddProducts(null);
        } catch (Exception e) {            
            return new ModelAndView("producto/registrar_producto")
                    .addObject("exception", e.getMessage());
        }
        
    }
}    
