package co.unicolombo.edu.controllers;

import co.unicolombo.edu.services.storage.StorageServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jimmy
 */
@RestController
@RequestMapping("/assets")
public class AssetController {
    @Autowired
    private StorageServiceImp storageService;
    
    @GetMapping("/{filename:.+}")
    public Resource getResource(@PathVariable("filename") String filename){
        return storageService.loadAsResource(filename);
    }
}
