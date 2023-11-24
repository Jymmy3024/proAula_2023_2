package co.unicolombo.edu.controllers;

import co.unicolombo.edu.models.AdminTienda;
import co.unicolombo.edu.models.Cliente;
import co.unicolombo.edu.models.Usuario;
import co.unicolombo.edu.services.AdminTiendaServicio;
import co.unicolombo.edu.services.IClienteServicio;
import co.unicolombo.edu.util.MensajeController;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class ControladorLogin {

    private final IClienteServicio clienterServicio;
    @Autowired
    private AdminTiendaServicio adminServicio;

    @Autowired
    public ControladorLogin(IClienteServicio clienterServicio) {
        this.clienterServicio = clienterServicio;
    }

    @GetMapping("/clienteLogin")
    public String login(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/iniciarSesion")
    public String login(@ModelAttribute("usuario") Usuario user, Model modelo, RedirectAttributes redirectAttributes) {

        Cliente iniciar = clienterServicio.loginCliente(user.getCorreo(), user.getPassword());
        if (iniciar == null) {
            modelo.addAttribute("mensajeError", "Email o contraseña incorrecta");
            return "login";
        } else {

            redirectAttributes.addFlashAttribute("nUser", iniciar.getNombre1().charAt(0));
            return "redirect:/inicio";
        }
    }

    @GetMapping("/login/otros")
    public String otrosLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login/login_others";
    }

    @PostMapping("/login/otros")
    public ModelAndView otrosLogin(@RequestParam("admin") String admin, @RequestParam("repartidor") String repartidor, Usuario usuario, HttpSession sesion) {
        ModelAndView modelo = new ModelAndView("login/login_others");
        try {
            System.out.println(admin);
            System.out.println(repartidor);
            
            if (admin != null && !admin.isEmpty()) {

                if (this.adminServicio.exists(usuario.getCedula())) {
                    //admin existe
                    AdminTienda adminsesion = (AdminTienda) this.adminServicio.login(usuario.getCorreo(), usuario.getPassword());

                    if (adminsesion != null) {
                        sesion.setAttribute("admin", adminsesion);
                        return new ModelAndView("redirect: /tienda/listar/productos");
                    }

                } else {
                    //admin no existe

                    modelo.addObject("mensajeError", "Email o contraseña incorrecta");

                }

            } else if (repartidor != null && !repartidor.isEmpty()) {

            }
            return modelo;
        } catch (Exception e) {
            e.printStackTrace();
            return modelo.addObject("mensajeError", e.getMessage());
        }

    }
}
