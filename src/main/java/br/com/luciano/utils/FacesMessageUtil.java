/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luciano.utils;

import javax.faces.application.FacesMessage;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Luciano
 */
public class FacesMessageUtil {
    
    public static void success(){
        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Operação realizada com sucesso!"), true);
    }
    public static void dynamicError(String dynamicMessage){
        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem", dynamicMessage), true);
    }
    public static void error(){
        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem", "Operação não realizada! Contate nosso suporte"), true);
    }
    public static void dynamicAlert(String dynamicMessage){
        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensagem", dynamicMessage), true);
    }
    
}
