package co.unicolombo.edu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class Repartidor extends Usuario{ //Hereda de la clase Usuario
    
    @Column(name="estado", nullable=true, length=30)
    String estado = "Inactivo";
    
    
    @Column(name="calificacion", nullable=true)
    int calificacion;
            
    
    @Override
    public String toString(){
        return "Repartidor {"
                + "Usuario = "+super.toString()
                + ", estado = "+this.estado
                + ", calificacion = "+this.calificacion
                + "}";
    }    
}
