/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.controllers;

import co.unicolombo.edu.models.AdminTienda;
import co.unicolombo.edu.models.Tienda;
import co.unicolombo.edu.services.AdminTiendaServicio;
import co.unicolombo.edu.services.TiendaServicioImp;
import co.unicolombo.edu.services.UsuarioServicio;
import co.unicolombo.edu.util.Mensaje;
import co.unicolombo.edu.util.MensajeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author CDOG
 */
@Controller
public class AdminTiendaController {

    @Autowired
    AdminTiendaServicio adminServicio;

    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    TiendaServicioImp tServicio;

    @GetMapping("tiendas/administradores/crear/{nit}")
    public ModelAndView mostrarFormulario(@PathVariable("nit") Integer nit) {
        ModelAndView modelo = new ModelAndView("admin_tienda/crear_admin");
        Tienda tienda = tServicio.obtenerPorNit(nit);
        AdminTienda admin = new AdminTienda();
        admin.setTienda(tienda);
        modelo.addObject("adminTienda", admin);
        //Enviamos el formulario suponiendo que ya el admin ya esta en la tabla Usuarios 
        modelo.addObject("usuarioExiste", true);
        return modelo;
    }

    @PostMapping("tiendas/administradores/crear/{nit}")
    public ModelAndView procesarFormularioAdmin(@PathVariable("nit") Integer nit, @Validated @Param("adminTienda") AdminTienda adminTienda, BindingResult resultado) {

        try {
            ModelAndView modelo = new ModelAndView("admin_tienda/crear_admin");
            Tienda tienda = tServicio.obtenerPorNit(nit);

            //Verificamos si no hay un Usuario ya con esa cedula
            boolean usuarioExiste = usuarioServicio.existeByCedula(adminTienda.getCedula());

            adminTienda.setTienda(tienda);
            if (resultado.hasErrors()) {

                if (usuarioExiste) {
                    //usuario existe
                    //verificamos si hay errores en los datos propios del admin 
                    if (resultado.hasFieldErrors("cedula") || resultado.hasFieldErrors("cargo")) {
                        //si si volvemos a mandar al modelo
                        modelo.addObject("usuarioExiste", usuarioExiste);
                        return modelo;
                    } else {
                        //no hay errores en los datos del admin
                        //se puede guardar
                        
                        return this.guardarAdmin(adminTienda);

                    }
                } else {
                    //usuario no existe
                    //hay errores en los demas campos ya que el usuario no existe
                    //se mostraran todos los campos por llenar
                    modelo.addObject("usuarioExiste", usuarioExiste);
                    return modelo;
                }
            }
            //no hay errores en los datos del admin, se puede pasar a guardar
            return this.guardarAdmin(adminTienda);

        } catch (Exception e) {
            e.printStackTrace();
            //Mandamos un mensaje de error
            return new MensajeController().mostrarException(e);
        }
    }

    public ModelAndView guardarAdmin(AdminTienda adminTienda) {

        try {
            //Verificamos si no hay un Usuario ya con esa cedula
            boolean usuarioExiste = usuarioServicio.existeByCedula(adminTienda.getCedula());            
            //Si todo esta bien podremos validar y guardar el admin
            System.out.println(adminTienda);

            adminTienda = this.adminServicio.saveAdminTienda(adminTienda);
            
            //si se guardo, mandamos un mensaje de exito
            Mensaje mensajeExito = new Mensaje("Administrador guardado",
                    "El administrador se guardo con exito.\nYa puede ir a iniciar sesion.");
            return new MensajeController().mensajeExitoso(mensajeExito).addObject("admin", "admin");

        } catch (JpaSystemException e) {
            Mensaje advertencia = new Mensaje("Error", e.getMessage());
            return new MensajeController().mensajeAdvertencia(advertencia);
        } catch (Exception e) {
            e.printStackTrace();
            //Mandamos un mensaje de error
            return new MensajeController().mostrarException(e);
        }

    }
}
