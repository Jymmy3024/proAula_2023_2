package co.unicolombo.edu.controllers;

import co.unicolombo.edu.models.Repartidor;
import co.unicolombo.edu.services.IRepartidorServicio;
import co.unicolombo.edu.services.UsuarioServicio;
import co.unicolombo.edu.util.Mensaje;
import co.unicolombo.edu.util.MensajeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author CDOG
 */
@Controller
@RequestMapping("/repartidores")
public class RepartidorController {

    @Autowired
    IRepartidorServicio repartidorServicio;

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/crear")
    public ModelAndView mostrarFormulario() {
        ModelAndView modelo = new ModelAndView("repartidor/crear_repartidor");
        Repartidor repartidor = new Repartidor();
        modelo.addObject("repartidor", repartidor);
        //Enviamos el formulario suponiendo que ya el admin ya esta en la tabla Usuarios 
        modelo.addObject("usuarioExiste", true);
        return modelo;
    }

    @PostMapping("/crear")
    public ModelAndView procesarFormularioAdmin(@Validated Repartidor repartidor, BindingResult resultado) {

        try {
            ModelAndView modelo = new ModelAndView("repartidor/crear_repartidor");

            //Verificamos si no hay un Usuario ya con esa cedula
            boolean usuarioExiste = usuarioServicio.existeByCedula(repartidor.getCedula());
            System.out.println(usuarioExiste);
            if (usuarioExiste) {
                //usuario existe
                return new MensajeController().mensajeAdvertencia(new Mensaje("Error", "Este usuario ya existe."));
            }
            if (resultado.hasErrors()) {
                //usuario no existe
                //hay errores en los demas campos ya que el usuario no existe
                //se mostraran todos los campos por llenar
                modelo.addObject("usuarioExiste", usuarioExiste);
                return modelo;
            }
            //no hay errores en los datos del admin, se puede pasar a guardar

            return guardarRepartidor(repartidor);

        } catch (Exception e) {
            e.printStackTrace();
            //Mandamos un mensaje de error
            return new MensajeController().mostrarException(e);
        }
    }

    public ModelAndView guardarRepartidor(Repartidor repartidor) {

        try {
            //Verificamos si no hay un Usuario ya con esa cedula
            //boolean usuarioExiste = usuarioServicio.existeByCedula(repartidor.getCedula());            
            //Si todo esta bien podremos validar y guardar el admin

            repartidor = repartidorServicio.saveRepartidor(repartidor);

            //si se guardo, mandamos un mensaje de exito
            Mensaje mensajeExito = new Mensaje("Repartidor guardado",
                    "Usted se registro con exito.\nYa puede ir a iniciar sesion.");
            return new MensajeController().mensajeExitoso(mensajeExito);

        } catch (Exception e) {
            e.printStackTrace();
            //Mandamos un mensaje de error
            return new MensajeController().mostrarException(e);
        }

    }
}
