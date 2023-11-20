/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author York Severiche
 */
/**
 *
 * @author CDOG
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("USUARIO") //DIFERENCIA EL TIPO AL QUE PERTENECE

@Inheritance(strategy = InheritanceType.JOINED) //Indicamos que habra herencia 
           // InheritanceType.JOINED -> la tabla padre y las tablas hijas por separado
@Table(name = "usuarios")//INDICAMOS LA TABLA A LA QUE HACE REFERENCIA EN LA BASE DE DATOS
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Pattern(regexp = "^[0-9]*$", message="La cedula solo acepta numeros")
    @NotBlank 
    @Id 
    @Column(name="cedula", nullable=false, length = 10)
    private String cedula;
    
    @Pattern(regexp = "[a-zA-Z]{2,15}", message = "El nombre solo acepta letras")
    @NotBlank
    @Column(name="nombre1", nullable=false, length = 15)
    private String nombre1;
    
    @Pattern(regexp = "[a-zA-Z]{0,15}", message = "El nombre solo acepta letras")
    @Column(name="nombre2", nullable=true, length=15)
    private String nombre2;
    
    @Pattern(regexp = "[a-zA-Z]{2,15}", message = "El apellido solo acepta letras")
    @NotBlank
    @Column(name="apellido1", nullable=false, length=15)
    private String apellido1;
        
    @Pattern(regexp = "[a-zA-Z]{0,15}", message = "El apellido solo acepta letras")
    @Column(name="apellido2", nullable=true, length=15)
    private String apellido2;
        
    @Email(message = "Digite un email valido")
    @NotEmpty(message = "El correo es requerido") 
    @Column(name="correo", nullable=false, length=40, unique=true)//EL CORREO ES UNICO
    private String correo;
        
    @NotEmpty(message = "La contraseña es requerida")
    @Size(max = 20)
    @Column(name = "password", length = 20, nullable=false)
    private String password;
    
    @NotNull(message = "La fecha es requerida")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past(message = "La fecha de nacimiento debe ser anterior a la actual")
    @Column(name = "fecha_nacimiento")
    private LocalDate fecha_nacimiento;    
    
    @NotBlank
    @Column(name="genero", nullable=false, length=10)
    private String genero;
    

    @Override
    public String toString(){
        return  "Usuario {"
                + " cedula = "+this.cedula
                + ", nombre1 = "+this.nombre1
                + ", nombre2 = "+this.nombre2
                + ", apellido1 = "+this.apellido1
                + ", apellido2 = "+this.apellido2
                + ", correo = "+this.correo
                + ", password = "+this.password
                + ", fecha_nacimiento = "+this.fecha_nacimiento
                + ", genero = "+this.genero
                + "}";
    }

}
