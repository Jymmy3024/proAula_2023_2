
package co.unicolombo.edu.repositories;

import co.unicolombo.edu.models.ProductoGlobal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author CDOG
 */
@Repository
public interface ProductoGlobalRepository extends JpaRepository<ProductoGlobal, Integer>{
    
    @Query(value =  "SELECT * FROM productos_global pg WHERE CONCAT(pg.nombre, pg.descripcion, pg.codigo) LIKE %?1%", nativeQuery = true)
    public List<ProductoGlobal> findAll(String palabraClave);
    
}
