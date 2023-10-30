package co.unicolombo.edu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categorias", schema = "dbo")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @NotEmpty
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @NotEmpty
    @Column(name = "detalles", nullable = false, length = 200)
    private String detalles;

    //Una categoria tiene muchos ProductosGlobales
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<ProductoGlobal> listaProductosGlobales;

    /* 
    fetch = FetchType.LAZY : Consulta perezosa: no se cargaran los 
    productos cada vez que se cree una categoria
    sino que cuande se llame al atributo
    
    mappedBy:  el atributo de la clase que recibe la referencia

    no se coloca columna ya que no es necesaria
     */

    @Override
    public String toString() {
        return "Categoria{"
                + " id = " + this.id
                + ", nombre = " + this.nombre
                + ", detalles = " + this.detalles
                + " }";
    }

}
