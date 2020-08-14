/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luciano.exceptions;

/**
 *
 * @author Luciano
 */
public class ObjectNotFoundException extends RuntimeException {
    
    public ObjectNotFoundException(String msg){
        super(msg);
    }
    
}
