package co.unicolombo.edu.controllers;

import co.unicolombo.edu.models.Categoria;
import co.unicolombo.edu.models.Producto;
import co.unicolombo.edu.models.ProductoGlobal;
import co.unicolombo.edu.models.Tienda;
import co.unicolombo.edu.services.CategoriaServicio;
import co.unicolombo.edu.services.ITiendaServicio;
import co.unicolombo.edu.services.ProductoGlobalServicio;
import co.unicolombo.edu.services.storage.StorageServiceImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author CDOG
 */
@Controller
public class ProductoGlobalController {

    @Autowired
    private ProductoGlobalServicio pgservice;
    @Autowired
    private CategoriaServicio cservice;
    @Autowired
    private StorageServiceImp storageService;
    @Autowired
    private ITiendaServicio tServicio;        

    @GetMapping("producto_global/registrar")
    public ModelAndView showRegistrationGlobalProductsForm() {

        ProductoGlobal pg = new ProductoGlobal();
        List<Categoria> categorias = cservice.listAll();
        return new ModelAndView("producto_global/crear_producto_global")
                .addObject("productoGlobal", pg)
                .addObject("categorias", categorias);
    }

    @PostMapping("producto_global/agregar")
    public ModelAndView saveGlobalProduct(@Validated ProductoGlobal productoGlobal, BindingResult resultado, Model model) {
        try {
            if (resultado.hasErrors() || productoGlobal.getImagen().isEmpty()) {
                System.out.println(resultado.getFieldError());
                if (productoGlobal.getImagen() == null || productoGlobal.getImagen().isEmpty()) {
                    resultado.rejectValue("imagen", "MultipartNotEmpty");
                }
                List<Categoria> categorias = cservice.listAll();
                return new ModelAndView("producto_global/crear_producto_global")
                        .addObject("productoGlobal", productoGlobal)
                        .addObject("categorias", categorias);
            }
            productoGlobal = pgservice.validar(productoGlobal);
            //if (productoGlobal.getImagen() != null && !productoGlobal.getImagen().isEmpty()) {
                String ruta_imagen = storageService.storage(productoGlobal.getImagen());
                productoGlobal.setRuta_imagen(ruta_imagen);
                pgservice.save(productoGlobal);
                
                Producto producto = new Producto();
                Tienda t = tServicio.obtenerPorNit(1000);
                producto.setTienda(t);
                producto.setProductoGlobal(productoGlobal);

                return new ModelAndView("producto/registrar_producto")
                        .addObject("producto", producto);
            //}
        } catch (Exception e) {
            List< Categoria> categorias = cservice.listAll();
            return new ModelAndView("producto_global/crear_producto_global")
                    .addObject("productoGlobal", productoGlobal)
                    .addObject("categorias", categorias)
                    .addObject("exception", e.getMessage());
        }
    }
}
