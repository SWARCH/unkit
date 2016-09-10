/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic.controller;

import java.util.List;
import dataAcces.entity.Employee;
import dataAccess.dao.EmployeeDAO;

/**
 *
 * @author Mauricio
 */
public class HandleEmployee {
    
    
    public String createEmployee(String name, String role, double salary, String contractType, String contractStatus, String userid) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmployeeRole(role);
        employee.setSalary(salary);
        employee.setContractType(contractType);
        employee.setContractStatus(contractStatus);
        employee.setUserid(userid);
        System.out.println("Handle" + employee);
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee ex = employeeDAO.persist(employee);
        
       if ( ex != null) {
           return "la cuenta ha sido creada, su número de cuenta es " + employee.getUserid() + ".";
       } else {
           return "no se pudo crear la cuenta";
       }
    }
    
    public List<Employee> getEmployeeList() {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        return employeeDAO.searchAll();
    }
    
    public void dismiss(String id) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee candidate = employeeDAO.searchByUserid(id);
        employeeDAO.editContractStatus(candidate, "INACTIVO");
    }
    
    public Employee extractEmployee(String userid){
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = employeeDAO.searchByUserid(userid);
        
        if(employee!=null)
            return employee;
        else
            return null;        
    }
    
    public void editEmployee(String userid,String userName){
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = employeeDAO.searchByUserid(userid);
        employeeDAO.editName(employee, userName);
    }
    
    public void quit(String userid){
        this.dismiss(userid);
    }
}
