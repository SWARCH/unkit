package dataAccess.dao;

import dataAcces.entity.Employee;
import dataAcces.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A DAO class for the Employee entity.
 * @author Mauricio
 */
public class EmployeeDAO {

    public EntityManagerFactory emf;
    public EntityManager em;

    public EmployeeDAO() {
        emf = Persistence.createEntityManagerFactory("UNKITPU");
        em = emf.createEntityManager();
    }
    
    public Employee searchByUserid(String userid) {
        Employee employee = null;
        try {
            employee = em.find(Employee.class, userid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
        return employee;
    }
    
    /**
     * Makes an Employee instance managed and persistent.
     * @param employee the Employee instance to create in the database
     * @return the same Employee instance if the operation was successful
     */
    public Employee persist(Employee employee) {
        em.getTransaction().begin();
        try {
            em.persist(employee);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
        return employee;
    }
    
    public boolean editContractStatus(Employee employee, String contractStatus) {
        Employee employeeNew;
        boolean success = true;
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
    
    public List<Employee> searchAll() {
        List<Employee> employees = em.createNamedQuery("Employee.findAll").getResultList();
        return employees;
    }
    
    public Employee findByUsername(String username) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.searchByUsername(username);
        String userId = user.getId();
        Employee employee = this.searchByUserid(userId);
        return employee;
    }

}
