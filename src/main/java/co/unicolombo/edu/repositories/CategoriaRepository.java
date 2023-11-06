
package co.unicolombo.edu.repositories;

import co.unicolombo.edu.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author CDOG
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
           
}
