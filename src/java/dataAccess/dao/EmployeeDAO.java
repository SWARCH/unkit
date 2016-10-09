/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess.dao;

import dataAcces.entity.Employee;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Mauricio
 */
public class EmployeeDAO {

    public EntityManagerFactory emf1 = 
            Persistence.createEntityManagerFactory("UNKITPU2");
    
    public Employee persist(Employee employee) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            System.out.println(employee);
            em.persist(employee);
            em.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            Set<ConstraintViolation<?>> a;
            a = e.getConstraintViolations();
            for (ConstraintViolation cv : a) {
                System.out.println(cv);
            }
            return null;
        } finally {
            em.close();
        }
        return employee;
    }
    
    public Employee searchByUserid(String userid) {
        EntityManager em = emf1.createEntityManager();
        Employee employee = null;
        try {
            employee = em.find(Employee.class, userid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return employee;
    }
    
    public boolean editContractStatus(Employee employee, String contractStatus) {
        Employee employeeNew;
        boolean success = true;
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            employeeNew = em.merge(em.find(Employee.class, employee.getUserid()));
            employeeNew.setContractStatus(contractStatus);
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
    
    public boolean editSalary(String userid, Double salary) {
        Employee employeeNew;
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        boolean success = true;
        try {
            employeeNew = em.merge(em.find(Employee.class, userid));
            employeeNew.setSalary(salary);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            success = false;
        } finally {
            em.close();
        }
        return success;
    }
    
    public boolean editName(Employee employee, String nameEmployee){
        Employee newEmployee;
        boolean success = true;
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            newEmployee = em.merge(em.find(Employee.class, employee.getUserid()));
            newEmployee.setName(nameEmployee);
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
    
    public boolean editContract(Employee employee, String stateContract){
        Employee newEmployee;
        boolean success = true;
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            newEmployee = em.merge(em.find(Employee.class, employee.getUserid()));
            newEmployee.setContractStatus(stateContract);
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
    
    public List<Employee> findAll() {
        EntityManager em = emf1.createEntityManager();
        List<Employee> employees = em.createNamedQuery("Employee.findAll").getResultList();
        return employees;
    }

}
