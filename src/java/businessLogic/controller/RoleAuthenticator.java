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
import presentation.bean.UserBean;

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
    public static final String EMPLOYEE = "EMPLOYEE";
    
    public static final String ENSAMBLADORA = "ENSAMBLADORA";
    public static final String MAYORISTA = "MAYORISTA";

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
        
        System.out.println("Validar usuario ROlAuth: " + id);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.searchById(id);
        
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
                    switch(employee.getEmployeeRole()){
                        case MANAGER:
                            System.out.println("Esta entrando al manager en roleAUTH " + employee.toString());
                            return "manager";
                        case MANUFACTURER:
                            return "employee";
                        /*case EMPLOYEE:
                            System.out.println("Esta entrando al employer en roleAUTH " + employee.toString());
                            return "employer";*/
                        
                        default:
                            return "employee";
                    }
                    //if (employee.getEmployeeRole().equals(MANAGER)) {
                    //    return "manager";
                    //} else if (employee.getEmployeeRole().equals(MANUFACTURER)) {
                    //    return "manufacturer";
                    //} else {
                    //    return "employe";
                    //}
                } else if (isCustomer(id)) {
                   CustomerDAO cxDAO = new CustomerDAO();
                    Customer cx = cxDAO.searchByUserid(id);
                    switch(cx.getType()){
                        case ENSAMBLADORA:
                            return "ensambladora";
                        case MAYORISTA:
                            return "mayorista";
                        /*case EMPLOYEE:
                            System.out.println("Esta entrando al employer en roleAUTH " + employee.toString());
                            return "employer";*/
                        
                        default:
                            return "verifique sus datos"; // crear clase catalogo xhtml. que permita ver todo
                    }
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
        User user = userDAO.searchById(id);
        
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = employeeDAO.searchByUserid(id);
        if(employee != null)
         return employee.getUserid().equals(user.getId());
        else return false;
    }
    
    public boolean isCustomer(String id) {
        // This method needs to be improved for perfomance
        UserDAO userDAO = new UserDAO();
        User user = userDAO.searchById(id);
        
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.searchByUserid(id);
        if (customer != null)
        return customer.getUserid().equals(user.getId());
        else return false;
    }
    
}
