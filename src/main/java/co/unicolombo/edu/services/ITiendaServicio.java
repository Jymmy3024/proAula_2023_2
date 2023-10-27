package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Tienda;
import java.util.List;


public interface ITiendaServicio {
    public List<Tienda> listarTiendas() throws Exception;
    
    public void agregarTienda(Tienda tienda) throws Exception;
    
    public void editarTienda(Tienda tienda) throws Exception;
    
    public void eliminarTienda(Tienda tienda) throws Exception;
    
    public Tienda buscarTienda(Tienda tienda) throws Exception;
    
    public List<Tienda> buscarTiendaPorNombre(String nombre) throws Exception;
    
    public boolean existeTienda(Tienda tienda) throws Exception;
            
           
}
