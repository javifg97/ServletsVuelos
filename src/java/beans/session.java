/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author javier.fernandez3
 */
@Stateless
public class session {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceUnit
    EntityManagerFactory emf;

    public List findAll() {
        return emf.createEntityManager().createNamedQuery("Vuelos.findAll").getResultList();
    }
    
    
    public List findByCriteria(String origen,String destino,String fecha){
        
        
        Query query = emf.createEntityManager().createNamedQuery("Vuelos.findByTodo");
        query.setParameter("diayhora", fecha);
        query.setParameter("origen", origen);
        query.setParameter("destino", destino);
        return query.getResultList();
        
        
    }

}
