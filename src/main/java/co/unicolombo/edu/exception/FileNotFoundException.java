/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author jimmy
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException{
    public FileNotFoundException( String message){
        super(message);
    }
    
    public FileNotFoundException(String message, Throwable exception){
        super(message, exception);
    }
}
