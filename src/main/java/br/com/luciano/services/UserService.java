/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luciano.services;


import br.com.luciano.domain.Usuario;
import br.com.luciano.generics.GenericService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;

/**
 *
 * @author Luciano
 */
@Named(value = "userService")
public class UserService extends GenericService<Usuario>{

    @Override
    public void save(Usuario entity) {
       super.save(entity); 
    }

    @Override
    public void update(Usuario entity) {
        super.update(entity); 
    }
    
    @Override
    public void delete(Usuario entity) {
        entity = findById(entity.getId());
        super.delete(entity); 
    }
    
    public Usuario findById(Long id){
       return super.findById(Usuario.class, id);
    }
    
    public List<Usuario> getAll(){
        try {
            return getResultListNamedQuery(Usuario.USERS_GET_ALL, null);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return new ArrayList();
    }
    
     
    @Override
    public Usuario getSingleResultNamedQuery(String query, Map<String, Object> parametros) {
        return super.getSingleResultNamedQuery(query, parametros); 
    }

    @Override
    public List<Usuario> getResultListNamedQuery(String query, Map<String, Object> parametros) throws Exception {
        return super.getResultListNamedQuery(query, parametros); 
    }
    console.log("Erro")
  
}
