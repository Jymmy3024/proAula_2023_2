
package co.unicolombo.edu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author CDOG
 */
@Entity
@Table(name = "productos", schema="dbo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable=false)
    private int id;        
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY) //un producto pertenece a una tienda
    @JoinColumn(name="tienda", nullable=false) //joincolumn porque es una relacion
    private Tienda tienda;
    
    @ManyToOne(optional=false, fetch=FetchType.LAZY) //Un producto pertenece a un producto global
    @JoinColumn(name="producto_global", nullable = false)
    private ProductoGlobal productoGlobal;
    
    
    @Column(name="stock", nullable=true)
    private int stock;
    
    
    @Column(name="disponibilidad", nullable=false)
    private boolean disponibilidad = true;//valor por defecto true
    
    @NotNull
    @Column(name="precio_unitario", nullable=false, precision = 10, scale = 4)
    private BigDecimal precioUnitario;
    
    @NotNull
    @Column(name="pum", nullable=true, precision = 10, scale = 4)
    private BigDecimal pum;
    
    @Column(name="estado", nullable=true)
    private String estado = "Activo";
    
    @Override
    public String toString(){
        return "Producto{"
                + "id = "+this.id
                + ", tienda = "+this.tienda
                + ", productoGlobal = "+this.productoGlobal
                + ", stock = "+this.stock
                + ", disponibilidad = "+this.disponibilidad
                + ", precio_unitario = "+this.precioUnitario
                + ", pum = "+this.pum
                + ", estado = "+this.estado
                + "}";
    }    
}
