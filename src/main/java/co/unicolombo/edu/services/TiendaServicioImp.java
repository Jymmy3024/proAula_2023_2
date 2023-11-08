package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Producto;
import co.unicolombo.edu.models.Tienda;
import co.unicolombo.edu.repositories.ProductoRepository;
import co.unicolombo.edu.repositories.TiendaRepository;
import java.util.ArrayList;
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
    public Page<Tienda> listarTiendas(@PageableDefault(sort = "nombre", size = 8) Pageable pageable) throws Exception {
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
    public List<Tienda> listarPorCategoria(String tipo) throws Exception{
        List<Tienda> listCT = tiendaRepo.findByTipo(tipo);
        if(listCT == null || listCT.isEmpty()){
            throw new Exception("No hay registros de tiendas con la categoria: " + tipo);
        }else{
            return listCT;
        }
        
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
        List<Tienda> tiendas = this.tiendaRepo.findAll(); //Obtenemos la lista de tiendas
        List<Tienda> resultados = new ArrayList<Tienda>();
        
        //recorremos la lista de tiendas para ir haciendo la busqueda en sus productos
        for (Tienda t : tiendas) {
            
            //Obtenemos la lista de productos encontrados en esa tienda correspondientes a la busqueda
            List<Producto> productosTienda = productoRepo.findAllByTienda(t.getNit(), busqueda);
            
            //guardamos las tiendas en la que hayan resultados
            if (productosTienda != null && !productosTienda.isEmpty()) {                
                t.setListaProductos(productosTienda);
                resultados.add(t);
            }            
        }
        
        for(Tienda t:resultados){
            System.out.println("TIENDA: "+t.getNombre());
            for(Producto p : t.getListaProductos()){
                System.out.println("P:"+p.getPrecioUnitario()+"PG:"+p.getProductoGlobal().getNombre());
            }
            System.out.println("\n\n");
        }
        return resultados;
    }

}
