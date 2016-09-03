/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.bean;

import businessLogic.controller.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * <p>A simple managed bean to mediate between the user and 
 * the <code>RoleAuthenticator</code>.</p>
 * @author mauricio
 */
@ManagedBean
@ViewScoped
public class UserBean {
    
    private String message;
    
    /** <p>User properties.</p> */
    private String id;
    private String username;
    private String password;
    private String tradeName;
    private String type;
    
    /** Employee properties**/
    private String name;
    private String employeeRole;
    private double salary;
    private String contractType;
    private String contractStatus;
    


    public UserBean() {
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
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
 public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradename) {
        this.tradeName = tradename;
    }
    
     public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String validateUser() {
        RoleAuthenticator roleAuthenticator = new RoleAuthenticator();
        message = roleAuthenticator.validateUser(id, username, password);
        return message;
    }
    
    public String logout() {
        HttpSession session = (HttpSession)
             FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "login";
    }
    
    public void createAccount(){
        HandleUser createAccount = new HandleUser();
        HandleCustomer cx = new HandleCustomer();
        this.setMessage(createAccount.createUser(username, password, id));
        this.setMessage(cx.createAccount(id, tradeName, type));
    }
    
    public void createEmployee() {
        System.out.println(this);
        HandleUser createUser = new HandleUser();
        HandleEmployee employeeCreator = new HandleEmployee();
        this.setMessage(createUser.createUser(username, password, id) + 
                employeeCreator.createEmployee(name, employeeRole, salary, contractType, contractStatus, id));
    }

    @Override
    public String toString() {
        return "userBean: " + username +" "+ id +" "+ name +" "+ salary +" "+ contractStatus +" "+ contractType +" "+  password ;
    }
    
    
}
