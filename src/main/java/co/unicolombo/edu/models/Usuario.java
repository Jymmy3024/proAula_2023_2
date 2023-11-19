/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author York Severiche
 */
@Entity
@Table(name = "usuarios")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Pattern(regexp = "[0-9]+")
    @Size(max = 15, message = "La cedula debe contener max 15 caracteres")
    @NotEmpty(message = "La cedula es requerida")
    @Column(name = "cedula",nullable = false, length = 15)
    private String cedula;
    
    @NotBlank
    @NotEmpty
    @Size(max = 20, message = "La Primer Nombre debe contener max 20 caracteres")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El nombre no debe contener caracteres especiales")
    @Column(name = "nombre1", length = 45, nullable=false)
    private String nombre1;
    
    @Size(max = 20, message = "La Segundo Nombre debe contener max 20 caracteres")
    @Column(name = "nombre2", length = 20, nullable=true)
    private String nombre2;
    
    @NotBlank
    @NotEmpty
    @Size(max = 20, message = "La Primer Apellido debe contener max 20 caracteres")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El apellido no debe contener caracteres especiales")
    @Column(name = "apellido1", length = 20, nullable=false, unique = true)
    private String apellido1;
    
    @Column(name = "apellido2", length = 45, nullable=true)
    private String apellido2;
    
    @Email(message = "Digite un email valido")
    @NotEmpty(message = "El correo es requerido") 
    @Size(max = 45, message = "La Correo debe contener max 45 caracteres")
    @Column(name = "correo", length = 45, nullable=false, unique = true)
    private String correo;
    
    @NotEmpty(message = "La contraseña es requerida")
    @Size(max = 20, message = "El Password debe contener max 20 caracteres")
    @Column(name = "password", length = 20, nullable=false)
    private String password;
    
    @NotNull(message = "La fecha es requerida")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past(message = "La fecha de nacimiento debe ser anterior a la actual")
    @Column(name = "fecha_nacimiento")
    private LocalDate fecha_nacimiento;
    
    @NotEmpty
    @Length(min = 1, max = 1)
    @Column(name = "genero", length = 1, nullable=false)
    private String genero;
    
}
