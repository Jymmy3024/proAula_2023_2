
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Categoria;
import java.util.List;

/**
 *
 * @author CDOG
 */
public interface CategoriaServicio {
    
    public List<Categoria> listAll();
    
    public Categoria getById(Integer id);
}
