/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess.dao;

import dataAcces.entity.User;
import dataAcces.entity.Vehicle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
/**
 *
 * @author VangsPardz
 */
public class VehicleDAO {
        public EntityManagerFactory emf1 = 
            Persistence.createEntityManagerFactory("UNKITPU");
        
     public List<Vehicle> findAll() {
        EntityManager v = emf1.createEntityManager();
        List<Vehicle> vs = v.createNamedQuery("Vehicle.findAll").getResultList();
        return vs;
    }
     
     public Vehicle searchByID(String id) {
        EntityManager em = emf1.createEntityManager();
        Vehicle v = null;
        try {
            v = em.find(Vehicle.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return v;
    }

    public Vehicle persist(Vehicle newVehicle) {
        EntityManager em = emf1.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(newVehicle);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return null;
        } finally {
            em.close();
        }
        return newVehicle;
    }
}
