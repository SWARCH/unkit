/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess.dao;

import dataAcces.entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mauricio
 */
public class CustomerDAO {
    
    public EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("UNKITPU");
    
    public Customer searchByUserid(String userid) {
        EntityManager em = emf.createEntityManager();
        Customer customer = null;
        try {
            customer = em.find(Customer.class, userid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return customer;
    }
    
}
