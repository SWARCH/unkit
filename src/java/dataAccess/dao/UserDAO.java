/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess.dao;

import dataAcces.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * A DAO class for the user entity;
 *
 * @author Mauricio
 */
public class UserDAO {

    public EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("UNKITPU");

    public User searchByID(String id) {
        EntityManager em = emf.createEntityManager();
        User user = null;
        try {
            user = em.find(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return user;
    }

    public User searchByUsername(String username) {
        // TODO This method is wrong, DON'T USE IT yet until it is correct.
        EntityManager em = emf.createEntityManager();
        User user = null;
        Query q = em.createNamedQuery("User.findByUsername");
        q.setParameter("username",username);
        try {
            //user = em.find(User.class, username);
            user = (User) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            em.close();
        }
        return user;
    }

    public User persist(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
    }
}
