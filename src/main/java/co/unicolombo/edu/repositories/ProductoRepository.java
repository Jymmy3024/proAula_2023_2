/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.unicolombo.edu.repositories;

import co.unicolombo.edu.models.Producto;
import co.unicolombo.edu.models.Tienda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CDOG
 */
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    Page<Producto> findAllByTienda(Tienda tienda, Pageable pageable);
}
