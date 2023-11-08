/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.unicolombo.edu.repositories;

import co.unicolombo.edu.models.Producto;
import java.util.List;
import co.unicolombo.edu.models.Tienda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author CDOG
 */
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT * FROM productos p WHERE p.tienda = ?1 "
            + "AND p.producto_global IN "
            + "(SELECT codigo FROM productos_global pg WHERE "
            + "CONCAT(pg.nombre, pg.descripcion, pg.codigo)"
            + "LIKE %?2%)",
            nativeQuery = true)
    public List<Producto> findAllByTienda(Integer tienda, String busqueda);
    
    Page<Producto> findAllByTienda(Tienda tienda, Pageable pageable);
}
