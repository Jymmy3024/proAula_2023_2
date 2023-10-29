
package co.unicolombo.edu.controllers;

import co.unicolombo.edu.models.ProductoGlobal;
import co.unicolombo.edu.services.ProductoGlobalServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author CDOG
 */
@Controller
public class ProductoController {
    
    @Autowired
    ProductoGlobalServicio servicio;
    
     
    @GetMapping("productos/agregar")//@param es para obtener el parametro de busqueda "?busqueda="
    public String showFormAddProducts(Model model, @Param("busqueda") String busqueda){        
        List<ProductoGlobal> resultados = servicio.listAll(busqueda);
        model.addAttribute("productos", resultados);
        model.addAttribute("busqueda", busqueda);
        return "agregar_producto";
    }

}
