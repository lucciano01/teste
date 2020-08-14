/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luciano.generics;


import static br.com.luciano.generics.PersistenceFactory.em;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Luciano
 * @param <T>
 */
public class GenericService<T> implements Serializable {

    @Inject
    PersistenceFactory persistence;

    private EntityTransaction tx;

    public void save(T entity) {
        tx = persistence.getEm().getTransaction();
        tx.begin();
        persistence.getEm().persist(entity);
        tx.commit();
      
    }

    public void update(T entity) {
        tx = persistence.getEm().getTransaction();
        tx.begin();
        persistence.getEm().merge(entity);
        tx.commit();
    }
    
    public void delete(T entity) {
        tx = persistence.getEm().getTransaction();
        tx.begin();
        persistence.getEm().remove(entity);
        tx.commit();

    }

    public T findById(Class clazz, Long id) {
        return (T) em.find(clazz, id);
    }

    public T getSingleResultNamedQuery(String query, Map<String, Object> parametros) {
        try {
            return (T) createNamedQuery(query, parametros).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    public List<T> getResultListNamedQuery(String query, Map<String, Object> parametros) throws Exception {
        return (List<T>) createNamedQuery(query, parametros).getResultList();
    }

    private Query createNamedQuery(String query, Map<String, Object> parametros) {
        Query q = persistence.getEm().createNamedQuery(query);

        if (parametros != null) {
            for (Map.Entry<String, Object> param : parametros.entrySet()) {
                q.setParameter(param.getKey(), param.getValue());
            }

        }

        return q;
    }
    
    public Long maxId(){
       return (Long) persistence.getEm().createNativeQuery("select max(id) from user").getSingleResult();
    }
    
   
    

}
