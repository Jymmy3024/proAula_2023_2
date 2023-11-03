
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Categoria;
import co.unicolombo.edu.repositories.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CDOG
 */
@Service
public class CategoriaServicioImpl implements CategoriaServicio{

    @Autowired
    private CategoriaRepository repositorio;
    
    @Override
    public List<Categoria> listAll() {
        return repositorio.findAll();
    }

    @Override
    public Categoria getById(Integer id) {
        return repositorio.findById(id).get();
    }
    
    
    
}
