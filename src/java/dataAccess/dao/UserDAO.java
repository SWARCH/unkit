package dataAccess.dao;

import dataAcces.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A DAO class for the User entity;
 * @author Mauricio
 */
public class UserDAO {
    
    public EntityManagerFactory emf;
    public EntityManager em;

    public UserDAO() {
        emf = Persistence.createEntityManagerFactory("UNKITPU");
        em = emf.createEntityManager();
    }
    
    public User searchById(String id) {
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
        User user = (User) em.createNamedQuery("User.findByUsername")
                .setParameter("username", username).getSingleResult();
        return user;
    }
    
    public User persist(User user) {
        em.getTransaction().begin();
        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
    }
}
