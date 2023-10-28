
package co.unicolombo.edu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**@author CDOG*/

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos_global", schema="dbo")
public class ProductoGlobal implements Serializable{
        
    @Id     //Indicamos que es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indicamos que la llave se genera automaticamente     
    @Column(name = "codigo", nullable = false) //Indicamos la columna en la tabla
    private int codigo;
    
    @Column(name="nombre", nullable=false, length=45, unique=true)
    private String nombre;
        
    @ManyToOne(optional = false)
    @JoinColumn(name="categoria", nullable = false) //Se pone JoinColumn para indicar que esta columna sera de relacion
    private Categoria categoria;
    
    @OneToMany(mappedBy = "productoGlobal", fetch = FetchType.LAZY) 
    private List<Producto> subProductos;
        
    @NotEmpty
    @NotBlank
    @Column(name="descripcion", nullable=false, length = 200)
    private String descripcion;
    
    @NotEmpty
    @NotBlank
    @Column(name="imagen", nullable=true, length=200)
    private String imagen;
    
    @NotEmpty
    @NotBlank
    @Column(name="estado", nullable=true, length=15)
    private String estado;
        
}
