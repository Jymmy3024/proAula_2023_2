package co.unicolombo.edu.controllers;


import co.unicolombo.edu.models.Tienda;
import co.unicolombo.edu.services.ITiendaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author jimmy
 */
@Controller
public class TiendaController {
    @Autowired
    private ITiendaServicio tiendaCrud;
    
    @GetMapping("inicio/")
    public String inicio(Model model) throws Exception{
        try{
            List<Tienda> tiendaList = tiendaCrud.listarTiendas();
            model.addAttribute("tiendaList", tiendaList);
        }catch(Exception e){
            String msjIni = e.getMessage();
            model.addAttribute("msjIni", msjIni);
        }
        return "index";
    }
    @GetMapping("inicio/registrar-tienda")
    public String registrar(Tienda tienda, Model model){
        model.addAttribute("tienda", tienda);
        return "tienda/registrarTienda";
    }
}
