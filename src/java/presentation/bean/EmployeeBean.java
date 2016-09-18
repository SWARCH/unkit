/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.bean;

import businessLogic.controller.HandleEmployee;
import dataAcces.entity.Employee;
import dataAcces.entity.User;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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
    
    private final FacesContext faceContext;
    private final HttpServletRequest httpServletRequest;
    
    public EmployeeBean() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
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
        User userLoged = new User();
        userLoged = (User) httpServletRequest.getSession().getAttribute("sessionUser");
        HandleEmployee handleEmployee = new HandleEmployee();
        Employee employee = handleEmployee.extractEmployee(userLoged.getId());
        if(employee!=null){
            setUserid(employee.getUserid());
            setName(employee.getName());
            setEmployeeRole(employee.getEmployeeRole());
            setSalary(employee.getSalary());
            setContractStatus(employee.getContractStatus());
            setContractType(employee.getContractType()); 
        }
    }
    
    public void quitWorkEmployee() {
        User userLoged = new User();
        userLoged = (User) httpServletRequest.getSession().getAttribute("sessionUser");
        HandleEmployee handleEmployee= new HandleEmployee();
        handleEmployee.quitWork(userLoged.getId());
    }
    
    public void editNameEmployee(){
        User userLoged = new User();
        userLoged = (User) httpServletRequest.getSession().getAttribute("sessionUser");
        HandleEmployee handleEmployee = new HandleEmployee();
        handleEmployee.editEmployee(userLoged.getId(), name);
    }
}
