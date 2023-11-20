package co.unicolombo.edu.repositories;

import co.unicolombo.edu.models.AdminTienda;
import co.unicolombo.edu.models.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author CDOG
 */
@Repository
public interface AdminTiendaRepository extends JpaRepository<AdminTienda, String>{
    
    public boolean existsByCedulaAndTienda(String cedula, Tienda tienda);
    
}
