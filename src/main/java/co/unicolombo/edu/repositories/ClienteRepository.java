/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.unicolombo.edu.repositories;

import co.unicolombo.edu.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author York Severiche
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    Cliente findByCorreoAndPassword(String correo, String password);
}
