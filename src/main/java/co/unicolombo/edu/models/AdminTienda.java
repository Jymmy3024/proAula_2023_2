
package co.unicolombo.edu.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@DiscriminatorValue("ADMIN")//Esto se usa para poder identificar de manera diferente un hijo de una mismo padre, y asi spring boot no piense que vamos a insertar otra vez el mismo id
@PrimaryKeyJoinColumn(name="usuario", referencedColumnName = "cedula") 
       //referencedColumnName : El nombre de la columna id de la tabla padre en la bd a la que hace referencia     
@Table(name="administradores_tienda")
public class AdminTienda extends Usuario{ //Hereda de la clase Usuario
    
    @NotBlank
    @Column(name="cargo", nullable=true, length=30)
    String cargo = "General";
    
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="tienda",nullable = false)
    Tienda tienda;
    
    
    public AdminTienda(Usuario usuario, String cargo, Tienda tienda){
        this.setCedula(usuario.getCedula());
        this.setNombre1(usuario.getNombre1());
        this.setNombre2(usuario.getNombre2());
        this.setApellido1(usuario.getApellido1());
        this.setApellido2(usuario.getApellido2());
        this.setFecha_nacimiento(usuario.getFecha_nacimiento());
        this.setGenero(usuario.getGenero());
        this.setCorreo(usuario.getCorreo());
        this.setPassword(usuario.getPassword());
        
        this.setCargo(cargo);
        this.setTienda(tienda);
    }
    
    
    @Override
    public String toString(){
        return "AdminTienda {"
                + "Usuario = "+super.toString()
                + ", cargo = "+this.cargo
                + ", tienda = "+this.tienda.toString()
                + "}";
    }
}
