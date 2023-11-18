package co.unicolombo.edu.repositories;

import co.unicolombo.edu.models.Tienda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jimmy
 */

public interface TiendaRepository extends JpaRepository<Tienda, Integer>{
    
    List<Tienda> findByNombreContaining(String nombre);
    List<Tienda> findByTipo(String tipo);
    
}
