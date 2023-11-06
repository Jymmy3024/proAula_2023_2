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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Tienda t = tServicio.obtenerPorNit(1000);
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
                return new ModelAndView("producto/registrar_producto")
                .addObject("producto", producto);
        }      
        try {
            //Recuperamos la tienda y el producto global
            Tienda tienda = tServicio.obtenerPorNit(producto.getTienda().getNit());
            ProductoGlobal productoGlobal = pgServicio.getByCodigo(producto.getProductoGlobal().getCodigo());
            
            //Los volvemos a asignar a el producto ya encontrado
            producto.setTienda(tienda);
            producto.setProductoGlobal(productoGlobal);
            //validamos el producto
            producto = pServicio.validar(producto);
            //guardamos el producto
            pServicio.guardarProducto(producto);
            
            return this.showFormAddProducts(null);
        } catch (Exception e) {            
            return new ModelAndView("producto/registrar_producto")
                    .addObject("exception", e.getMessage());
        }
        
    }
    
    @PostMapping("inicio/listar/productos")
    public ModelAndView listarProductos(@RequestParam(name = "nit", required = false) Integer nit,Pageable pageable){
        System.out.println("El nit es: " +nit);
        return new ModelAndView("listar_productos");
    }
}    
