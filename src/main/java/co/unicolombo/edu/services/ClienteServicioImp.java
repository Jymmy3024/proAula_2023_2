/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Cliente;
import co.unicolombo.edu.repositories.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author York Severiche
 */
@Service
public class ClienteServicioImp implements IClienteServicio {

    @Autowired
    ClienteRepository clienteRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Cliente> listarClientes() {
        return (List<Cliente>) clienteRepo.findAll();
    }

    @Transactional
    @Override
    public void guardarCliente(Cliente cliente) {
        clienteRepo.save(cliente);
    }

    @Transactional
    @Override
    public void eliminar(Cliente cliente) {
        clienteRepo.delete(cliente);
    }

    @Transactional(readOnly = true) //esto para que es? para buscar al cliente.  si pero el transactional yo creo que debo usar eso pero no se para que es, eso es para que solo lo lea
    @Override //ahh debe se algo del readonly ese. pero osea si uno no pone que sera lo que esta haciendo, porque a mi me sale algo de una sesion  , y yo no estoy iniciando sesion con el empledo ni nda
    public Cliente buscar(Cliente cliente) {
        String cedulaString = cliente.getCedula();
        int cedula = Integer.parseInt(cedulaString);
        return clienteRepo.findById(cedula).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente loginCliente(String correo, String password) {
        Cliente user = clienteRepo.findByCorreoAndPassword(correo, password);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean cedulaExistente(String cedula) {
        Cliente cliente = clienteRepo.findByCedula(cedula);
        return cliente != null;
    }

}
