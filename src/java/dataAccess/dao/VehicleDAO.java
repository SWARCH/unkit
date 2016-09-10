package dataAccess.dao;

import dataAcces.entity.Vehicle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A DAO class for de Vehicle entity.
 * @author VangsPardz
 */
public class VehicleDAO {
        public EntityManagerFactory emf;
        public EntityManager em;

    public VehicleDAO() {
        emf = Persistence.createEntityManagerFactory("UNKITPU");
        em = emf.createEntityManager();
    }
    
     public List<Vehicle> findAll() {
        List<Vehicle> vehicles = em.createNamedQuery("Vehicle.findAll")
                .getResultList();
        return vehicles;
    }
     
     public Vehicle searchById(String id) {
        Vehicle vehicle = null;
        try {
            vehicle = em.find(Vehicle.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return vehicle;
    }

    public Vehicle persist(Vehicle vehicle) {
        em.getTransaction().begin();
        try {
            em.persist(vehicle);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
        return vehicle;
    }
    
    /**
     * Check https://www.youtube.com/watch?v=NiG82Ie7dX0
     * @param query
     * @return 
     */
    public int checkIfQueryExists(String query) {
        EntityManager em = emf.createEntityManager();
        List<Vehicle> vehicles = 
                em.createQuery("SELECT v FROM Vehicle v WHERE v.trademark = :trademark")
                        .setParameter("trademark", query).getResultList();
        return vehicles.size();
    }
    
    // Suspect method. dont't trust it
    public Vehicle returnVehicle(String query) {
        Vehicle vehicle = (Vehicle) emf.createEntityManager().createQuery("SELECT v FROM Vehicle v WHERE v.trademark = :trademark").setParameter("trademark", query)
                .getSingleResult();
        //System.out.println("inside return v: " + vehicle);
        return vehicle;
    }
}
