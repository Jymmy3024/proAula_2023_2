package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Tienda;
import co.unicolombo.edu.repositories.TiendaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jimmy
 */
@Service
public class TiendaServicioImp implements ITiendaServicio{
    
    @Autowired
    private TiendaRepository tiendaRepo;
    @Override
    @Transactional(readOnly = true)
    public List<Tienda> listarTiendas() throws Exception {
        List<Tienda> listTienda = tiendaRepo.findAll();
        if(listTienda == null && listTienda.isEmpty()){
            throw new Exception("La lista de tiendas esta vacia.");
        }else{
         return listTienda;
        }
    }

    @Override
    public void agregarTienda(Tienda tienda) throws Exception {
        if(tiendaRepo.existsById(tienda.getCodigo())){
            throw new Exception("La Tienda con el codigo = " + tienda.getCodigo() + ", ya existe.");        
        }else{
            tiendaRepo.save(tienda);
        }
    }

    @Override
    public void editarTienda(Tienda tienda) throws Exception {
        if(!tiendaRepo.existsById(tienda.getCodigo())){
            throw new Exception("La Tienda que intenta editar con el codigo = " + tienda.getCodigo() + ", no existe.");        
        }else{
            tiendaRepo.save(tienda);
        }
    }

    @Override
    public void eliminarTienda(Tienda tienda) throws Exception {
        Tienda tfind = tiendaRepo.findById(tienda.getCodigo()).orElse(null);
        if(tfind == null){
            throw new Exception("La Tienda que desea elminar No existe.");
        }else{
            tiendaRepo.delete(tienda);
        }
    }

    @Override
    public Tienda buscarTienda(Tienda tienda) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Tienda> buscarTiendaPorNombre(String nombre) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existeTienda(Tienda tienda) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
