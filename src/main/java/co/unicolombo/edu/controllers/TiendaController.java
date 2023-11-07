package co.unicolombo.edu.controllers;

import co.unicolombo.edu.models.Tienda;
import co.unicolombo.edu.services.ITiendaServicio;
import co.unicolombo.edu.services.storage.StorageServiceImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jimmy
 */
@Controller
public class TiendaController {

    @Autowired
    private ITiendaServicio tiendaCrud;

    @Autowired
    private StorageServiceImp storageService;

    @GetMapping("inicio/")
    public ModelAndView inicio(@PageableDefault(sort = "nombre", size = 8)Pageable pageable) throws Exception {
        try {
            Page<Tienda> tiendaList = tiendaCrud.listarTiendas(pageable);
            return new ModelAndView("index")   
                    .addObject("tiendaList", tiendaList);
                    
        } catch (Exception e) {
            String msjIni = e.getMessage();
            return new ModelAndView("index")
                    .addObject("msjIni", msjIni);
        }

    }

    @GetMapping("inicio/listarCategoria/{tipo}")
    public ModelAndView listarByCategoria(@PathVariable("tipo") String tipo,Pageable pageable)throws Exception{
        try{
            List<Tienda> listTC = tiendaCrud.listarPorCategoria(tipo);
            return new ModelAndView("index")
                    .addObject("tiendaList", listTC);
        }catch(Exception e){
            return new ModelAndView("index")
                    .addObject("msjIni", e.getMessage());
        }
    }
    @GetMapping("inicio/registrar-tienda")
    public ModelAndView registrar(Tienda tienda) {
        return new ModelAndView("tienda/registrarTienda")
                .addObject("tienda", new Tienda());
    }

    @PostMapping("inicio/guardar-tienda")
    public ModelAndView guardar(@Validated Tienda tienda, Errors errores, Model model) throws Exception {
        boolean tExist = tiendaCrud.existeTienda(tienda);
        try {
            if (errores.hasErrors() || tienda.getImagen().isEmpty()) {
                if (tienda.getImagen().isEmpty()) {
                    errores.rejectValue("imagen", "MultipartNotEmpty");
                }
                model.addAttribute("tienda", tienda);
                return new ModelAndView("tienda/registrarTienda")
                        .addObject("tienda", tienda);
            }else if(!tExist){
            String ruta_imagen = storageService.storage(tienda.getImagen());
            tienda.setRuta_imagen(ruta_imagen);
            }
            boolean tNExist = !tExist;
//            model.addAttribute("tNExist", tNExist);
            tiendaCrud.agregarTienda(tienda);
            String msjTR = "Tienda Registrada con exito,enviaremos un correo para seguir con el proceso";
            //model.addAttribute("msjTR", msjTR);
            return new ModelAndView("tienda/registrarTienda")
                    .addObject("tNExist", tNExist)
                    .addObject("msjTR", msjTR)
                    .addObject("tienda", new Tienda());
            
        } catch (Exception e) {
//            model.addAttribute("tExist", tExist);
//            model.addAttribute("msjTEx", "Error: " + e.getMessage());
            return new ModelAndView("tienda/registrarTienda")
                    .addObject("tExist", tExist)
                    .addObject("msjTEx", "Error: " + e.getMessage());
        }
    }
    
    @PostMapping("inicio/buscar-tiendas")
    public ModelAndView buscarTiendas(String nombre, Pageable pageable) throws Exception{
        Page<Tienda> listTNE = tiendaCrud.listarTiendas(pageable);
        try{
            if(nombre.isBlank() || nombre.isEmpty()){
                return new ModelAndView("index")
                        .addObject("msjBT", "El campo para buscar no debe estar vacio")
                        .addObject("tiendaList", listTNE);
            }else{
            List<Tienda> listTBN = tiendaCrud.buscarTiendasPorNombre(nombre);
            return new ModelAndView("index")
                    .addObject("tiendaList", listTBN);
            }
        }catch(Exception e){
            return new ModelAndView("index")
                    .addObject("msjBT", e.getMessage())
                    .addObject("tiendaList", listTNE);
        }
        
    }
}
