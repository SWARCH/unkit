/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic.controller;

import dataAcces.entity.Customer;
import dataAcces.entity.Employee;
import dataAcces.entity.User;
import dataAccess.dao.CustomerDAO;
import dataAccess.dao.EmployeeDAO;
import dataAccess.dao.UserDAO;
import javax.faces.context.FacesContext;

/**
 * A controller class to find the role of the user.
 * @author Mauricio
 */
public class RoleAuthenticator {
    
    /**
     * <p>The key for the session scoped attribute holding the
     * appropriate <code>User</code> instance.</p>
     */
    public static final String USER_SESSION_KEY = "user";
    
    public static final String MANAGER = "MANAGER";
    public static final String MANUFACTURER = "MANUFACTURER";

    public RoleAuthenticator() {
    }
    
    /**
     * 
     * @param id
     * @param username
     * @param password
     * @return 
     */
    public String validateUser(String id, String username, String password) {
        //FacesContext context = FacesContext.getCurrentInstance();
        UserDAO userDAO = new UserDAO();
        User user = userDAO.searchByID(id);
        
        if (user != null) {
            if(id.equals(user.getId()) && username.equals(user.getUsername())){
                if (!password.equals(user.getPassword())) {
                    return "Incorrect password";
                } 
                else 
                {
                if (isEmployee(id)) {
                    //context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, user);
                    EmployeeDAO employeeDAO = new EmployeeDAO();
                    Employee employee = employeeDAO.searchByUserid(id);
                    if (employee.getEmployeeRole().equals(MANAGER)) {
                        return "manager";
                    } else if (employee.getEmployeeRole().equals(MANUFACTURER)) {
                        return "manufacturer";
                    } else {
                        return "employee";
                    }
                    
                } else if (isCustomer(id)) {
                    return "customer";
                }
                return "error-user"; // puede error de llaves 
                                    //if(id.equals(user.getId()) && username.equals(user.getUsername())){
                                    //if (!password.eq
            }
                
            }
            else{
            return "verifique sus datos";
                    }   
        } else {
            return "Can't find the user";
        }
    }
    
    public boolean isEmployee(String id) {
        // This method needs to be improved for perfomance
        UserDAO userDAO = new UserDAO();
        User user = userDAO.searchByID(id);
        
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = employeeDAO.searchByUserid(id);
        
        return employee.getUserid().equals(user.getId());
    }
    
    public boolean isCustomer(String id) {
        // This method needs to be improved for perfomance
        UserDAO userDAO = new UserDAO();
        User user = userDAO.searchByID(id);
        
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.searchByUserid(id);
        
        return customer.getUserid().equals(user.getId());
    }
    
}
