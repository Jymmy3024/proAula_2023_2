package co.unicolombo.edu.repositories;

import co.unicolombo.edu.models.Tienda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jimmy
 */
@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Integer>{
    List<Tienda> findByName(String nombre);
}
