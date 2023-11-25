
package co.unicolombo.edu.models;

import jakarta.persistence.Column;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.EqualsAndHashCode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author CDOG
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true) //Los metodos equals y hashcode deben usar a la clase padre
@NoArgsConstructor
@AllArgsConstructor
//@DiscriminatorValue("REPARTIDOR")//Esto se usa para poder identificar de manera diferente un hijo de una mismo padre, y asi spring boot no piense que vamos a insertar otra vez el mismo id

       //referencedColumnName : El nombre de la columna id de la tabla padre en la bd a la que hace referencia     
@PrimaryKeyJoinColumn(name="usuario", referencedColumnName = "cedula") 
@Table(name="repartidores")
public class Repartidor extends Usuario{ 

    private static final long serialVersionUID = 1L;
//Hereda de la clase Usuario
    
    @Column(name="estado", nullable=true, length=30)
    String estado = "Inactivo";
    
    
    @Column(name="calificacion", nullable=true)
    int calificacion;
            
    @OneToMany(mappedBy = "repartidor", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;
    @Override
    public String toString(){
        return "Repartidor {"
                + "Usuario = "+super.toString()
                + ", estado = "+this.estado
                + ", calificacion = "+this.calificacion
                + "}";
    }    

}