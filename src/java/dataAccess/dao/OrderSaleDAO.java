package dataAccess.dao;


import dataAcces.entity.OrderSale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A DAO class for the OrderSale entity.
 * @author VangsPardz
 */
public class OrderSaleDAO {
    public EntityManagerFactory emf;
    public EntityManager em;

    public OrderSaleDAO() {
        emf = Persistence.createEntityManagerFactory("UNKITPU");
        em = emf.createEntityManager();
    }
    
    
    public OrderSale persist(OrderSale orderSale) {
        em.getTransaction().begin();
        try {
            em.persist(orderSale);
            em.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
        return orderSale;
    }
    
}
