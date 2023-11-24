/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.AdminTienda;
import co.unicolombo.edu.models.Tienda;
import co.unicolombo.edu.models.Usuario;
import co.unicolombo.edu.repositories.AdminTiendaRepository;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author CDOG
 */
@Service
public class AdminTiendaServicioImpl implements AdminTiendaServicio {

    @Autowired
    AdminTiendaRepository repositorio;

    @Autowired
    UsuarioServicio userService;

    @Autowired
    ITiendaServicio tiendaService;
    
    @Autowired
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public AdminTienda getByCedula(String cedula) {
        return this.repositorio.findById(cedula).get();
    }

    @Override
    public List<AdminTienda> findAll() {
        return this.repositorio.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exists(String cedula) {
        return this.repositorio.existsById(cedula);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsInTienda(String cedula, Tienda tienda) {
        return this.repositorio.existsByCedulaAndTienda(cedula, tienda);
    }

    @Override
    public List<AdminTienda> listByTienda(Tienda tienda) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    @Transactional
    public AdminTienda saveAdminTienda(AdminTienda adminTienda) throws Exception {

        if (adminTienda != null) {
            adminTienda.setCedula(adminTienda.getCedula().trim());
            adminTienda.setCargo(adminTienda.getCargo().trim());
        } else {
            throw new Exception("AdminTienda es nulo");
        }

        if (adminTienda.getCedula().length() < 10) {
            throw new Exception("Error en la cedula: la cedula debe ser mayor o igual a 10 caracteres");
        }

        if (!this.tiendaService.existeTienda(adminTienda.getTienda())) {
            throw new Exception("Error: La tienda a la que intenta registrarse no existe");
        }

        if (this.exists(adminTienda.getCedula())) {

            String sCedula = adminTienda.getCedula();
            Tienda Tienda = adminTienda.getTienda();
            //Verificamos si existe en la tienda
            if (this.existsInTienda(sCedula, Tienda)) {
                throw new Exception(
                        "Error: El Administrador con cedula "
                        + adminTienda.getCedula()
                        + " Ya existe en la tienda"
                        + Tienda.getNombre());
            }
        }

        if (this.userService.buscarByCedula(adminTienda.getCedula()) != null) {
            //throw new Exception("Error: El usuario al que intenta registrarse no existe");
            //}else{
            //Colocamos los datos del usuario existente
            Usuario usuarioExistente = this.userService.buscarByCedula(adminTienda.getCedula());
            //adminTienda = new AdminTienda(usuarioExistente, adminTienda.getCargo(), adminTienda.getTienda());
            //adminTienda.setCedula(usuarioExistente.getCedula());
            adminTienda.setNombre1(usuarioExistente.getNombre1());
            adminTienda.setNombre2(usuarioExistente.getNombre2());
            adminTienda.setApellido1(usuarioExistente.getApellido1());
            adminTienda.setApellido2(usuarioExistente.getApellido2());
            adminTienda.setFecha_nacimiento(usuarioExistente.getFecha_nacimiento());
            adminTienda.setGenero(usuarioExistente.getGenero());
            adminTienda.setCorreo(usuarioExistente.getCorreo());
            adminTienda.setPassword(usuarioExistente.getPassword());

        } else {
            //quitamos espacios innecesarios de los datos obligatorios
            adminTienda.setNombre1(adminTienda.getNombre1().trim());
            adminTienda.setApellido1(adminTienda.getApellido1().trim());
            adminTienda.setPassword(adminTienda.getPassword().trim());
            adminTienda.setGenero(adminTienda.getGenero().trim());
            adminTienda.setCorreo(adminTienda.getCorreo().trim());

        }

        //Usuario u = this.userService.buscarByCedula(adminTienda.getCedula());
        Period edad = Period.between(adminTienda.getFecha_nacimiento(), LocalDate.now());
        //El admin debe ser mayor a 18 años
        if (edad.getYears() < 18) {
            throw new Exception("Fecha de nacimiento invalida: La edad debe ser mayor o igual a 18 años");
        }

        //System.out.println(this.repositorio.getReferenceById(adminTienda.getCedula()));
        
        return this.repositorio.save(adminTienda);
        //Si ningun error fue encontrado podemos guardar el Administrador        
        //this.repositorio.save(adminTienda);
    }

    @Override
    public AdminTienda login(String correo, String password) {
        AdminTienda admin = null;
        
        if(correo != null && !correo.isEmpty() && password != null && !password.isEmpty()){            
            
            if(this.repositorio.existsByCorreoAndPassword(correo, password)){
               admin = this.repositorio.findByCorreo(correo);
            }
        }
        
        return admin;
    }

}
