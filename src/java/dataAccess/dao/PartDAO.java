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
 *
 * @author Mauricio
 */
public class PartDAO {
    
    public EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("UNKITPU");
    
    public Part persist(Part newPart) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(newPart);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
        return newPart;
    }
    
    public Part searchById(String id) {
        EntityManager em = emf.createEntityManager();
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
    
    public boolean update(Part part) {
        Part partNew;
        boolean success = true;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            partNew = em.merge(em.find(Part.class, part.getId()));
            //partNew.setContractStatus(contractStatus);
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
