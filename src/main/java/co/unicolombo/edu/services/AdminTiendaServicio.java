
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.AdminTienda;
import co.unicolombo.edu.models.Tienda;
import java.util.List;

/**
 *
 * @author CDOG
 */
public interface AdminTiendaServicio {
    
    public AdminTienda getByCedula(String cedula);
    
    public List<AdminTienda> findAll();
    
    public boolean exists(String cedula);
    
    public boolean existsInTienda(String cedula, Tienda tienda);
    
    public AdminTienda saveAdminTienda(AdminTienda adminTienda) throws Exception;
    
    public List<AdminTienda> listByTienda(Tienda tienda);    
    
    public AdminTienda login(String correo, String password);

    public boolean existsByCorreo(String correo);
}
