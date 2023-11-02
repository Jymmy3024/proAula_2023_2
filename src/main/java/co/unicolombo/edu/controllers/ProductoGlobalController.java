package co.unicolombo.edu.controllers;

import co.unicolombo.edu.models.Categoria;
import co.unicolombo.edu.models.Producto;
import co.unicolombo.edu.models.ProductoGlobal;
import co.unicolombo.edu.models.Tienda;
import co.unicolombo.edu.services.CategoriaServicio;
import co.unicolombo.edu.services.ProductoGlobalServicio;
import co.unicolombo.edu.services.storage.StorageServiceImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("producto_global/registrar")
    public ModelAndView showRegistrationGlobalProductsForm(/*Model model*/) {

        ProductoGlobal pg = new ProductoGlobal();
        //model.addAttribute("productoGlobal", pg);
        List<Categoria> categorias = cservice.listAll();
        //model.addAttribute("categorias", categorias);
        return new ModelAndView("producto_global/crear_producto_global")
                .addObject("productoGlobal", pg)
                .addObject("categorias", categorias);
    }

    @PostMapping("producto_global/agregar")
    public ModelAndView saveGlobalProduct(ProductoGlobal productoGlobal, Model model) {
        try {
            productoGlobal = pgservice.validar(productoGlobal);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String ruta_imagen = storageService.storage(productoGlobal.getImagen());
        productoGlobal.setRuta_imagen(ruta_imagen);
        pgservice.save(productoGlobal);

        return new ModelAndView("redirect:producto/registrar_producto")
                .addObject("productoGlobal", productoGlobal)
                .addObject("tienda", new Tienda(888, "", "ara", "", "", null))
                .addObject("producto", new Producto());
    }
}
