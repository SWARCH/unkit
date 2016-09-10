/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess.dao;

import dataAcces.entity.Part;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A DAO class for the Part entity.
 * @author Mauricio
 */
public class PartDAO {
    
    public EntityManagerFactory emf;
    public EntityManager em;

    public PartDAO() {
        emf = Persistence.createEntityManagerFactory("UNKITPU");
        em = emf.createEntityManager();
    }
    
    public Part persist(Part part) {
        em.getTransaction().begin();
        try {
            em.persist(part);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
        return part;
    }
    
    public Part searchById(String id) {
        Part part = null;
        try {
            part = em.find(Part.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return part;
    }
    
    public boolean editName(Part part, String name) {
        Part partE;
        boolean success = true;
        em.getTransaction().begin();
        try {
            partE = em.merge(em.find(Part.class, part.getId()));
            partE.setName(name);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            success = false;
        } finally {
            em.close();
        }
        return success;
    }
    
}
