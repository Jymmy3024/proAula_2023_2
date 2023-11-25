package co.unicolombo.edu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author CDOG
 */
@Entity
@Table(
    name = "productos", schema = "dbo",
    uniqueConstraints = @UniqueConstraint(name="relacion_unica",
    columnNames = {"tienda", "producto_global"})
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY) //un producto pertenece a una tienda
    @JoinColumn(name = "tienda", nullable = false) //joincolumn porque es una relacion
    private Tienda tienda;

    @ManyToOne(optional = false, fetch = FetchType.LAZY) //Un producto pertenece a un producto global
    @JoinColumn(name = "producto_global", nullable = false)
    private ProductoGlobal productoGlobal;

    @NotNull(message = "El stock no puede ser nulo")
    @Max(value = Integer.MAX_VALUE, message = "Excediste el limite")
    @Positive(message = "El campo debe ser positivo")
    @Column(name = "stock", nullable = true)
    private int stock;

    @Column(name = "disponibilidad", nullable = false)
    private boolean disponibilidad = true;//valor por defecto true

    @NotNull
    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @Column(name = "pum", nullable = true)
    private BigDecimal pum;

    @Column(name = "estado", nullable = true)
    private String estado = "Activo";

    @ManyToMany
    @JoinTable(
            name = "detalle_pedido",
            joinColumns = @JoinColumn(name = "codigo_pedido"),
            inverseJoinColumns = @JoinColumn(name = "codigo_producto")
    )
    private List<Pedido> pedidos;
    
    
    @Override
    public String toString() {
        return "Producto{"
                + "id = " + this.id
                + ", tienda = " + this.tienda
                + ", productoGlobal = " + this.productoGlobal
                + ", stock = " + this.stock
                + ", disponibilidad = " + this.disponibilidad
                + ", precio_unitario = " + this.precioUnitario
                + ", pum = " + this.pum
                + ", estado = " + this.estado
                + "}";
    }
}
