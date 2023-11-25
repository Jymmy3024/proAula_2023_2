package co.unicolombo.edu.services;

import co.unicolombo.edu.models.AdminTienda;
import co.unicolombo.edu.models.Repartidor;
import co.unicolombo.edu.models.Usuario;
import co.unicolombo.edu.repositories.RepartidorRepository;
import java.time.LocalDate;
import java.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CDOG
 */
@Service
public class RepartidorServicioImpl implements IRepartidorServicio {

    @Autowired
    RepartidorRepository repo;

    @Autowired
    UsuarioServicio userService;

    @Override
    public boolean existeByCedula(String cedula) {
        return this.repo.existsById(cedula);
    }

    //@Transactional
    @Override
    public Repartidor saveRepartidor(Repartidor repartidor) throws Exception {

        if (repartidor != null) {
            repartidor.setCedula(repartidor.getCedula().trim());
        } else {
            throw new Exception("Repartidor es nulo");
        }

        if (repartidor.getCedula().length() < 10) {
            throw new Exception("Error en la cedula: la cedula debe ser mayor o igual a 10 caracteres");
        }

        if (this.userService.buscarByCedula(repartidor.getCedula()) != null) {
            //throw new Exception("Error: El usuario al que intenta registrarse no existe");
            //}else{
            //Colocamos los datos del usuario existente
            Usuario usuarioExistente = this.userService.buscarByCedula(repartidor.getCedula());
            repartidor.setNombre1(usuarioExistente.getNombre1());
            repartidor.setNombre2(usuarioExistente.getNombre2());
            repartidor.setApellido1(usuarioExistente.getApellido1());
            repartidor.setApellido2(usuarioExistente.getApellido2());
            repartidor.setFecha_nacimiento(usuarioExistente.getFecha_nacimiento());
            repartidor.setGenero(usuarioExistente.getGenero());
            repartidor.setCorreo(usuarioExistente.getCorreo());
            repartidor.setPassword(usuarioExistente.getPassword());

        }

        if (this.userService.existeByCorreo(repartidor.getCorreo())) {
            throw new Exception("Este correo ya se encuentra esta registrado");
        }

        //Usuario u = this.userService.buscarByCedula(adminTienda.getCedula());
        Period edad = Period.between(repartidor.getFecha_nacimiento(), LocalDate.now());
        //El admin debe ser mayor a 18 años
        if (edad.getYears() < 18) {
            throw new Exception("Fecha de nacimiento invalida: La edad debe ser mayor o igual a 18 años");
        }

        //System.out.println(this.repositorio.getReferenceById(adminTienda.getCedula()));
        return this.repo.save(repartidor);
        //Si ningun error fue encontrado podemos guardar el Administrador        
        //this.repositorio.save(adminTienda);
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return this.repo.existsByCorreo(correo);
    }

    @Override
    public Repartidor login(String correo, String password) {
        Repartidor repartidor = null;

        if (correo != null && !correo.isEmpty() && password != null && !password.isEmpty()) {

            if (this.repo.existsByCorreoAndPassword(correo, password)) {
                repartidor = this.repo.findByCorreo(correo);
            }
        }

        return repartidor;
    }

}
