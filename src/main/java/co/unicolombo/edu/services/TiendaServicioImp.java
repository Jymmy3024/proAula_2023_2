package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Producto;
import co.unicolombo.edu.models.Tienda;
import co.unicolombo.edu.repositories.ProductoRepository;
import co.unicolombo.edu.repositories.TiendaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

/**
 *
 * @author jimmy
 */
@Service
public class TiendaServicioImp implements ITiendaServicio {

    @Autowired
    private TiendaRepository tiendaRepo;

    @Autowired
    private ProductoRepository productoRepo;

    @Override
    public Page<Tienda> listarTiendas(@PageableDefault(sort = "tipo", size = 8) Pageable pageable) throws Exception {
        Page<Tienda> listTienda = tiendaRepo.findAll(pageable);
        if (listTienda == null && listTienda.isEmpty()) {
            throw new Exception("La lista de tiendas esta vacia.");
        } else {
            return listTienda;
        }
    }

    @Override
    public void agregarTienda(Tienda tienda) throws Exception {
        if (tiendaRepo.existsById(tienda.getNit())) {
            throw new Exception("La Tienda con el codigo = " + tienda.getNit() + ", ya existe.");
        } else {
            tiendaRepo.save(tienda);
        }
    }

    @Override
    public void editarTienda(Tienda tienda) throws Exception {
        if (!tiendaRepo.existsById(tienda.getNit())) {
            throw new Exception("La Tienda que intenta editar con el codigo = " + tienda.getNit() + ", no existe.");
        } else {
            tiendaRepo.save(tienda);
        }
    }

    @Override
    public void eliminarTienda(Tienda tienda) throws Exception {
        Tienda tfind = tiendaRepo.findById(tienda.getNit()).orElse(null);
        if (tfind == null) {
            throw new Exception("La Tienda que desea elminar No existe.");
        } else {
            tiendaRepo.delete(tienda);
        }
    }

    @Override
    public Tienda buscarTienda(Tienda tienda) throws Exception {
        Tienda tifind = tiendaRepo.findById(tienda.getNit()).orElse(null);
        if (tifind == null) {
            throw new Exception("La Tienda que desea buscar No existe.");
        } else {
            return tifind;
        }
    }

    @Override
    public List<Tienda> buscarTiendasPorNombre(String nombre) throws Exception {
        List<Tienda> listTi = tiendaRepo.findByNombreContaining(nombre);
        return listTi;
    }

    @Override
    public boolean existeTienda(Tienda tienda) {
        boolean tExist = tiendaRepo.existsById(tienda.getNit());
        return tExist;
    }

    @Override
    public Tienda obtenerPorNit(Integer nit) {
        return this.tiendaRepo.findById(nit).get();
    }

    @Override
    public List<Tienda> buscarProductosByTienda(String busqueda) {
        List<Tienda> tiendas = this.tiendaRepo.findAll();

        for (Tienda t : tiendas) {
            System.out.println(t.getNombre());
            t.setListaProductos(productoRepo.findProductoByTienda(t.getNit(), busqueda));
            if (t.getListaProductos() != null && !t.getListaProductos().isEmpty()) {
                for (Producto p : t.getListaProductos()) {
                    System.out.println(p);
                }
            }
            System.out.println("\n\n\n");
        }
        return tiendas;
    }

}
