/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess.dao;


import dataAcces.entity.OrderSale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author VangsPardz
 */
public class OrderSaleDAO {
    public EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("UNKITPU");
    public OrderSale persist(OrderSale os) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(os);
            em.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return os;
    }
    
}
