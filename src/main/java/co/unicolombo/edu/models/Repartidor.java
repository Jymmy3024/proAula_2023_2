/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jimmy
 */
@Entity
@Table(name = "repartidores")
@Data
@AllArgsConstructor
@NoArgsConstructor
class Repartidor implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Usuario usuario;
    
    private String estado;
    
    private String ubicaion;
    
    private Integer Calificacio;
            
    @OneToMany(mappedBy = "repartidor", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;
    
}
