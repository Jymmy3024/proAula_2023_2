
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

/**
 *
 * @author CDOG
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="locales")        
public class Local implements Serializable{

    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",  nullable = false)
    Integer id;
    
    @NotBlank
    @NotEmpty
    @Column(name="nombre", nullable=false, length=45, unique = true)
    String nombre;
    
    @NotBlank
    @NotEmpty
    @Column(name="direccion", nullable=false, length=100)
    String direccion;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="tienda", nullable = false)
    Tienda tienda;
    
    @OneToMany(mappedBy = "local_tienda", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;
    @Override
    public String toString(){
        return "Local{"+this.id
                + "nombre = "+this.nombre
                + "direccion = "+this.direccion
                + "tienda = "+this.tienda
                + "}";                
    }
    
    
}
