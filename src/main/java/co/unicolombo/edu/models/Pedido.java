/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jimmy
 */
@Entity
@Table(name = "pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", nullable = false)
    private int id;
    
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
    
    @Column(name = "direccion", nullable = false, length = 200)
    @NotNull(message =  "La direccion es un campo obligatorio")
    private String direccion;
    
    @Column(name = "fecha_pedido", nullable = false)
    private LocalDateTime fecha_pedido = LocalDateTime.now();
    
     @Column(name = "fecha_envio", nullable = true)
    private LocalDateTime fecha_envio;
    
     @Column(name = "fecha_llegada", nullable = true)
    private LocalDateTime fecha_llegada;
    
     @ManyToOne(optional = false, fetch = FetchType.LAZY)
     @JoinColumn(name = "repartidor", nullable = false)
    private Repartidor repartidor;
    
     @ManyToOne(optional = false , fetch = FetchType.LAZY) //un pedido tiene un loca
     @JoinColumn(name = "local_tienda", nullable = false)
     private Local local_tienda; 
     
     @ManyToOne(optional = false , fetch = FetchType.LAZY) //un pedido tiene un clienrte
     @JoinColumn(name = "cliente", nullable = false)      
    private Cliente cliente;
     
     @ManyToMany(mappedBy = "pedidos", fetch = FetchType.LAZY)
     private List<Producto> productos;
}
