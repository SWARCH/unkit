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

/**
 *
 * @author lorena
 */
public class LoginController {

    public static final String USER_SESSION_KEY = "user";

    public static final String MANAGER = "MANAGER";
    public static final String MANUFACTURER = "MANUFACTURER";
    public static final String EMPLOYEE = "EMPLOYEE";

    public static final String ENSAMBLADORA = "ENSAMBLADORA";
    public static final String MAYORISTA = "MAYORISTA";

    public User Login(String id, String passW) {

        UserDAO usuarioDAO = new UserDAO();
        if (usuarioDAO.searchByUsername(id) != null) {
            User usuario = new User();
            usuario = usuarioDAO.searchByUsername(id);
            if (usuario.getPassword().equals(passW)) {
                return usuario;
            }
            return null;
        } else {
            return null;
        }
    }

    public String validateUser(String id) {
        System.out.println("Validar usuario ROlAuth: " + id);

        if (isEmployee(id)) {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Employee employee = employeeDAO.searchByUserid(id);
            switch (employee.getEmployeeRole()) {
                case MANAGER:
                    System.out.println("Esta entrando al manager en roleAUTH " + employee.toString());
                    return "manager";
                case MANUFACTURER:
                    return "employee";
                default:
                    return "employee";
            }
        } else if (isCustomer(id)) {
            CustomerDAO cxDAO = new CustomerDAO();
            Customer cx = cxDAO.searchByUserid(id);
            switch (cx.getType()) {
                case ENSAMBLADORA:
                    return "ensambladora";
                case MAYORISTA:
                    return "mayorista";
                default:
                    return "verifique sus datos"; // crear clase catalogo xhtml. que permita ver todo
            }
        }else{
            System.out.println("No tenia rol");
            return "formulario";
        }
    }
    
    

    public boolean isEmployee(String id) {
        // This method needs to be improved for perfomance
        System.out.println("IsEmployee: " + id);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.searchByID(id);

        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = employeeDAO.searchByUserid(id);
        if (employee != null) {
            return employee.getUserid().equals(user.getId());
        } else {
            return false;
        }
    }

    public boolean isCustomer(String id) {
        // This method needs to be improved for perfomance
        UserDAO userDAO = new UserDAO();
        User user = userDAO.searchByID(id);

        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.searchByUserid(id);
        if (customer != null) {
            return customer.getUserid().equals(user.getId());
        } else {
            return false;
        }
    }
}
