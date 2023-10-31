package co.unicolombo.edu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "tiendas")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Tienda implements Serializable{
    
    private static final long SerialVersionUID = 1L;
    @Id
    @Column(name = "nit", nullable = false)
    @NotNull
    private int nit;
    @Column(name = "descripcion", nullable = true, length = 200)
    private String descripcion;
    @Column(name = "nombre", nullable = false, length = 45)
    @NotNull
    private String nombre;
    @Column(name = "ruta_imagen", nullable = true, length = 200)   
    private String ruta_imagen;
    @Column(name = "tipo", nullable = true, length = 15)
    @NotEmpty
    private String tipo;
    
    @Transient
    private MultipartFile imagen;
}
