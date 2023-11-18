package co.unicolombo.edu.services.storage;

import java.nio.file.Path;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jimmy
 */
public interface IStorageService {
    void init();
    public String storage(MultipartFile file);
    public Path load(String fileName);
    public Resource loadAsResource(String fileName);
    public void delete(String fileName);
           
}
