package co.unicolombo.edu.controllers;

import co.unicolombo.edu.models.AdminTienda;
import co.unicolombo.edu.models.Cliente;
import co.unicolombo.edu.models.Repartidor;
import co.unicolombo.edu.models.Usuario;
import co.unicolombo.edu.services.AdminTiendaServicio;
import co.unicolombo.edu.services.IClienteServicio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import javax.swing.text.Document;

import co.unicolombo.edu.services.IRepartidorServicio;
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
    private IRepartidorServicio repartidorService;

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
    public String login(@ModelAttribute("usuario") Usuario user, Model modelo,HttpSession sesion) {
        
        Cliente cliente = clienterServicio.loginCliente(user.getCorreo(), user.getPassword());
        if (cliente == null) {
            modelo.addAttribute("mensajeError", "Email o contraseña incorrecta");
            return "login";
        } else {
            sesion.setAttribute("user", cliente);
            
            return "redirect:/inicio";
        }
    }
    
    @GetMapping("/login/logout")
    public ModelAndView logout(HttpSession sesion, HttpServletRequest request){
        sesion = request.getSession(false);
        if(sesion != null){
        sesion.invalidate();
        }
        return new ModelAndView("redirect:/inicio");
    }

    @GetMapping("/login/otros")
    public String otrosLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login/login_others";
    }

    @PostMapping("/login/otros")
    public ModelAndView otrosLogin(@RequestParam("rol") String rol, Usuario usuario, HttpSession sesion) {
        ModelAndView modelo = new ModelAndView("login/login_others");
        try {
            System.out.println("seleccionado:" + rol);

            if (rol != null && !rol.isEmpty()) {
                if (rol.equals("admin")) {
                    if (this.adminServicio.existsByCorreo(usuario.getCorreo())) {
                        //admin existe
                        AdminTienda adminsesion =  this.adminServicio.login(usuario.getCorreo(), usuario.getPassword());

                        if (adminsesion != null) {
                            sesion.setAttribute("admin", adminsesion);
                            return new ModelAndView("redirect:/tienda/listar/productos");
                        }
                    }
                    //admin no existe
                    //o datos incorrectos
                    modelo.addObject("mensajeError", "Email o contraseña incorrecta");

                } else if (rol.equals("repartidor")) {
                    if (this.repartidorService.existsByCorreo(usuario.getCorreo())) {
                        //admin existe
                        Repartidor repartidorSesion = this.repartidorService.login(usuario.getCorreo(), usuario.getPassword());

                        if (repartidorSesion != null) {
                            sesion.setAttribute("repartidor", repartidorSesion);
                            return new ModelAndView("repartidor/index_repartidor");
                        }
                    }
                    //admin no existe
                    //o datos incorrectos
                    modelo.addObject("mensajeError", "Email o contraseña incorrecta");
                }
            }
            return modelo;
        } catch (Exception e) {
            e.printStackTrace();
            return modelo.addObject("mensajeError", e.getMessage());
        }
    }
}
