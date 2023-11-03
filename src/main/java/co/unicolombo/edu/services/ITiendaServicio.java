package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Tienda;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITiendaServicio {

    public Page<Tienda> listarTiendas(Pageable pageable) throws Exception;

    public void agregarTienda(Tienda tienda) throws Exception;

    public void editarTienda(Tienda tienda) throws Exception;

    public void eliminarTienda(Tienda tienda) throws Exception;

    public Tienda buscarTienda(Tienda tienda) throws Exception;

    public List<Tienda> buscarTiendasPorNombre(String nombre) throws Exception;

    public boolean existeTienda(Tienda tienda);

    public Tienda obtenerPorNit(Integer nit);

}
