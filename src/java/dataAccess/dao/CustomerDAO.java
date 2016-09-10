package dataAccess.dao;

import dataAcces.entity.Customer;
import dataAcces.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A DAO class for the Customer entity.
 * @author Mauricio
 */
public class CustomerDAO {
    
    public EntityManagerFactory emf;
    public EntityManager em;

    public CustomerDAO() {
        emf = Persistence.createEntityManagerFactory("UNKITPU");
        em = emf.createEntityManager();
    }
    
    public Customer searchByUserid(String userid) {
        Customer customer = null;
        try {
            customer = em.find(Customer.class, userid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
        return customer;
    }
    
    public Customer persist(Customer customer) {
        em.getTransaction().begin();
        try {
            em.persist(customer);
            em.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
        return customer;
    }
    
    public List<Customer> searchAll() {
        List<Customer> customers = em.createNamedQuery("Customer.findAll").getResultList();
        return customers;
    }
    
    public Customer findByUsername(String username) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.searchByUsername(username);
        String userId = user.getId();
        Customer customer = this.searchByUserid(userId);
        return customer;
    }
    
}
