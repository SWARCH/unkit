/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.bean;

import businessLogic.controller.HandleEmployee;
import dataAcces.entity.Employee;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mauricio
 */
@ManagedBean
@ApplicationScoped
public class EmployeeBean {
    private String message;
    
    private String name;
    private String employeeRole;
    private double salary;
    private String contractType;
    private String contractStatus;
    private String userid;
    
    public EmployeeBean() {
        
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String role) {
        this.employeeRole = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public void createEmployee() {
        HandleEmployee employeeCreator = new HandleEmployee();
        this.setMessage(employeeCreator.createEmployee(name, employeeRole, salary, contractType, contractStatus, userid));
    }
    
    public void employeeLoged(){
        System.out.println("Estoy en employeeLoged" + userid);
        HandleEmployee handleEmployee = new HandleEmployee();
        Employee employee = handleEmployee.extractEmployee(userid);
        if(employee!=null){
            setUserid(employee.getUserid());
            setName(employee.getName());
            setEmployeeRole(employee.getEmployeeRole());
            setSalary(employee.getSalary());
            setContractStatus(employee.getContractStatus());
            setContractType(employee.getContractType());  
            System.out.println("Hola: "+ name);
        }
    }
}
