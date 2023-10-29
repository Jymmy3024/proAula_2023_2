/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.services.storage;

import co.unicolombo.edu.exception.FileNotFoundException;
import co.unicolombo.edu.exception.StorageException;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jimmy
 */
@Service
public class StorageServiceImp implements IStorageService{

    @Value("${storage.location}")
    private String storageLocation;
    
    @PostConstruct
    @Override
    public void init() {
        try{
            Files.createDirectories(Paths.get(storageLocation));
        }catch(IOException e){
            throw new StorageException("Error al iniciar la ubicacion del almacen de archivos.");
        }
         
    }

    @Override
    public String storage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        
        if(file.isEmpty()){
            throw new StorageException("No se puede almacenar un archivo vacio");
        }
        try{
        InputStream inputStream = file.getInputStream();
        Files.copy(inputStream,Paths.get(storageLocation).resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            throw new StorageException("Error al almacenar el archivo " +fileName, e);
        }
        
        return fileName;
    }

    @Override
    public Path load(String fileName) {
        return Paths.get(storageLocation).resolve(fileName);
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try{
        Path file = load(fileName);
        Resource resource = new UrlResource(file.toUri());
        if(resource.exists() || resource.isReadable()){
            return resource;
        }else{
            throw new FileNotFoundException("No se pudo encontrar el archivo: " + fileName);
           }
        }catch(MalformedURLException e){
            throw new FileNotFoundException("No se pudo encontrar el archivo: " + fileName, e);
        }
    }

    @Override
    public void delete(String fileName) {
        Path file = load(fileName);
        try{
            FileSystemUtils.deleteRecursively(file);
        }catch(IOException e){
            
        }
    }
    
}
