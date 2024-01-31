/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author York Severiche
 */
@Entity(name = "clientes")
@Table(name = "clientes")
@Data //para los setter y los getter
@NoArgsConstructor //constructor vacio
@AllArgsConstructor //constructor con todo
@EqualsAndHashCode(callSuper = true) //eso es para que llame al padre.
@PrimaryKeyJoinColumn(name="usuario",referencedColumnName = "cedula")//referencedColumnName es para el de la tabla padre
                                                                    //y name es para la columna de la tabla hija
public class Cliente extends Usuario{

    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Column(name="direccion_envio")
    private String direccion_envio;
    
    
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;
}
