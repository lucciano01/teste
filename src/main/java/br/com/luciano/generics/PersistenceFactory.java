/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luciano.generics;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Luciano
 */
public class PersistenceFactory {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("TesteFedex_PU");
    public static EntityManager em;
    
     public EntityManager getEm() {
      if(em == null)   {
         em =  emf.createEntityManager();
      }
      return em; 
    }
    
}
