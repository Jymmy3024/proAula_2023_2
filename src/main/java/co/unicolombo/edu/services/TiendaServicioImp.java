package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Tienda;
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
public class TiendaServicioImp implements ITiendaServicio{
    
    @Autowired
    private TiendaRepository tiendaRepo;
    @Override
    public Page<Tienda> listarTiendas(@PageableDefault(sort = "nombre", size = 8) Pageable pageable) throws Exception {
        Page<Tienda> listTienda = tiendaRepo.findAll(pageable);
        if(listTienda == null && listTienda.isEmpty()){
            throw new Exception("La lista de tiendas esta vacia.");
        }else{
         return listTienda;
        }
    }

    @Override
    public void agregarTienda(Tienda tienda) throws Exception {
        if(tiendaRepo.existsById(tienda.getNit())){
            throw new Exception("La Tienda con el NIT = " + tienda.getNit() + ", ya existe.");        
        }else{
            tiendaRepo.save(tienda);
        }
    }

    @Override
    public void editarTienda(Tienda tienda) throws Exception {
        if(!tiendaRepo.existsById(tienda.getNit())){
            throw new Exception("La Tienda que intenta editar con el codigo = " + tienda.getNit() + ", no existe.");        
        }else{
            tiendaRepo.save(tienda);
        }
    }

    @Override
    public void eliminarTienda(Tienda tienda) throws Exception {
        Tienda tfind = tiendaRepo.findById(tienda.getNit()).orElse(null);
        if(tfind == null){
            throw new Exception("La Tienda que desea elminar No existe.");
        }else{
            tiendaRepo.delete(tienda);
        }
    }

    @Override
    public Tienda buscarTienda(Tienda tienda) throws Exception {
        Tienda tifind = tiendaRepo.findById(tienda.getNit()).orElse(null);
        if(tifind == null){
            throw new Exception("La Tienda que desea buscar No existe.");
        }else{
            return tifind;
        }
    }

    @Override
    public List<Tienda> buscarTiendasPorNombre(String nombre) throws Exception {
        List<Tienda> listTi = tiendaRepo.findByNombreContaining(nombre);
        if(listTi == null || listTi.isEmpty()){
            throw new Exception("La Tienda(s) que intenta buscar no existe.");
        }else{
            return listTi;
        }
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
    public boolean existeTienda(Tienda tienda){
        boolean tExist = tiendaRepo.existsById(tienda.getNit());
            return tExist;        
    }
    
}
