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
    private ProductoGlobalServicio pgServicio;//SERVICIO DEL PRODUCTO GLOBAL

    @Autowired
    private ProductoServicio pServicio;//SERVICIO DEL PRODUCTO DE LA TIENDA

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
        Tienda t = tServicio.obtenerPorNit(4112);
        Producto p = new Producto();
        p.setProductoGlobal(pgServicio.getByCodigo(codigoPg));
        p.setTienda(t);

        return new ModelAndView("producto/registrar_producto")
                .addObject("producto", p);
    }

    @PostMapping("productos/agregar")
    public ModelAndView guardarProducto(@Validated Producto producto, BindingResult result, Model modelo, HttpSession sesion) {
        if (result.hasErrors()) {
            return new ModelAndView("producto/registrar_producto")
                    .addObject("producto", producto);
        }
        try {

            //Recuperamos la tienda y el producto global
            Tienda tienda = tServicio.obtenerPorNit(producto.getTienda().getNit());
            ProductoGlobal productoGlobal = pgServicio.getByCodigo(producto.getProductoGlobal().getCodigo());

            //Los volvemos a asignar a el producto ya recuperados
            producto.setTienda(tienda);
            producto.setProductoGlobal(productoGlobal);

            //Validamos el producto
            producto = pServicio.validar(producto);

            //finalmente lo guardamos
            pServicio.guardarProducto(producto);

            return this.showFormAddProducts(null);
        } catch (Exception e) {
            return new ModelAndView("producto/registrar_producto")
                    .addObject("exception", e.getMessage());
        }
    }

    @GetMapping("productos/resultados")
    public ModelAndView showResultsPage(@Param("busqueda") String busqueda) {
        try {
            ModelAndView modelo = new ModelAndView("busqueda");
            
            if (busqueda != null && !busqueda.isEmpty() && !busqueda.isBlank()) {
                List<Tienda> tiendas = tServicio.buscarTiendasPorNombre(busqueda); //buscamos las tiendas con ese nombre
                List<Tienda> productosInTiendas = tServicio.buscarProductosByTienda(busqueda); //Buscamos los productos encontrados en cada tienda

                if (tiendas != null && !tiendas.isEmpty()) {
                    modelo.addObject("tiendas", tiendas);
                } else {
                    //si no hay tiendas con ese nombre colocamos aquellas donde hubo resultados
                    tiendas.addAll(productosInTiendas);
                    modelo.addObject("tiendas", tiendas);
                }

                if (productosInTiendas != null && !productosInTiendas.isEmpty()) {
                    modelo.addObject("productosInTienda", productosInTiendas);
                }
            }
            
            return modelo.addObject("busqueda", busqueda);

        } catch (Exception e) {
            return new ModelAndView("busqueda")
                    .addObject("exception", e);
        }
    }
}
