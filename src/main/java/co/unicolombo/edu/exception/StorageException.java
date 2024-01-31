/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicolombo.edu.exception;

/**
 *
 * @author jimmy
 */
public class StorageException extends RuntimeException{
    public StorageException( String message){
        super(message);
    }
    
    public StorageException(String message, Throwable exception){
        super(message, exception);
    }
}
