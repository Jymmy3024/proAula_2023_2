/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.services;

import co.unicolombo.edu.models.Repartidor;

/**
 *
 * @author CDOG
 */
public interface IRepartidorServicio {

    public boolean existeByCedula(String cedula);

    public Repartidor saveRepartidor(Repartidor repartidor) throws Exception;

    public boolean existsByCorreo(String correo);
    
    public Repartidor login(String correo, String password);
    
    
    
    
    
}
