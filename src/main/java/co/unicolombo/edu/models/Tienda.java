package co.unicolombo.edu.models;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tiendas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tienda implements Serializable{
    
    private static final long SerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", nullable = false)
    private int codigo;
    @Column(name = "descripcion", nullable = true, length = 200)
    private String descripcion;
    @Column(name = "nombre", nullable = false, length = 45)
    @NotBlank
    @NotEmpty
    private String nombre;
    @Column(name = "ruta_imagen", nullable = true, length = 200)
    private String ruta_imagen;
    @Column(name = "nit", nullable = false)
    @NotBlank
    @NotEmpty
    private int nit;
    @Column(name = "tipo", nullable = true, length = 15)
    private String tipo;
}
