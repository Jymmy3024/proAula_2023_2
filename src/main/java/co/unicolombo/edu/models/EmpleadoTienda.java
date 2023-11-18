/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author York Severiche
 */
@Entity
@Table(name = "empleados_tienda", schema = "dbo", catalog = "proaula_6")
@Data
public class EmpleadoTienda implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    private Usuario usuario;

    private String cargo;
    private String teinda_local;
    
    
}
