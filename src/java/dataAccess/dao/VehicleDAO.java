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

    public EntityManagerFactory emf1
            = Persistence.createEntityManagerFactory("UNKITPU2");

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

    /**
     * Check https://www.youtube.com/watch?v=NiG82Ie7dX0
     *
     * @param query
     * @return
     */
    public int checkIfQueryExists(String query) {
        EntityManager em = emf1.createEntityManager();
        List<Vehicle> vehicles
                = em.createQuery("SELECT v FROM Vehicle v WHERE v.trademark = :trademark")
                .setParameter("trademark", query).getResultList();
        return vehicles.size();
    }

    public Vehicle returnVehicle(String query) {
        Vehicle vehicle = (Vehicle) emf1.createEntityManager().createQuery("SELECT v FROM Vehicle v WHERE v.trademark = :trademark").setParameter("trademark", query)
                .getSingleResult();
        //System.out.println("inside return v: " + vehicle);
        return vehicle;
    }

    public boolean update(Vehicle vehicle) {
        System.out.println("DAO: " + vehicle.getId());
        System.out.println("DAO: " + vehicle.getTrademark());

        Vehicle vehicleNew;
        boolean success = true;
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            vehicleNew = em.merge(em.find(Vehicle.class, vehicle.getId()));
            vehicleNew.setId(vehicle.getId());
            vehicleNew.setTrademark(vehicle.getTrademark());
            vehicleNew.setColor(vehicle.getColor());
            vehicleNew.setCost(vehicle.getCost());
            vehicleNew.setDescription(vehicle.getDescription());
            vehicleNew.setModel(vehicle.getModel());
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
