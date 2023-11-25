
package co.unicolombo.edu.repositories;

import co.unicolombo.edu.models.Repartidor;
import co.unicolombo.edu.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author CDOG
 */
@Repository
public interface RepartidorRepository extends JpaRepository<Repartidor, String>{

    public boolean existsByCorreo(String correo);

    public boolean existsByCorreoAndPassword(String correo, String password);

    public Repartidor findByCorreo(String correo);
    
}
