/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.bean;

import businessLogic.controller.*;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import dataAcces.entity.User;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * A simple managed bean to mediate between the user and the
 * <code>RoleAuthenticator</code>.</p>
 *
 * @author mauricio
 */
@ManagedBean
@ApplicationScoped
public class UserBean {

    private String message;

    /**
     * <
     * p>
     * User properties.</p>
     */
    private String id;
    private String username;
    private String password;
    private String tradeName;
    private String type;

    private String passwordB;
    private String passwordC;
    /**
     * Employee properties*
     */
    private String name;
    private String employeeRole;
    private double salary;
    private String contractType;
    private String contractStatus;

    private String mensaje;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;

    public UserBean() {
        mensaje = "";
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
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

    public String getPasswordB() {
        return passwordB;
    }

    public void setPasswordB(String passwordB) {
        this.passwordB = passwordB;
    }

    public String getPasswordC() {
        return passwordC;
    }

    public void setPasswordC(String passwordC) {
        this.passwordC = passwordC;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String validateUser() {
        User userLoged = new User();
        userLoged = (User) httpServletRequest.getSession().getAttribute("sessionUser");
        LoginController roleAuthenticator = new LoginController();
        message = roleAuthenticator.validateUser(userLoged.getId());
        return message;
    }

    public void createAccount() {
        HandleUser createAccount = new HandleUser();
        HandleCustomer cx = new HandleCustomer();
        this.setMessage(createAccount.createUser(username, password, id));
        this.setMessage(cx.createAccount(id, tradeName, type));
    }

    public void createEmployee() {
        System.out.println(this);
        HandleUser createUser = new HandleUser();
        HandleEmployee employeeCreator = new HandleEmployee();
        this.setMessage(createUser.createUser(username, password, id)
                + employeeCreator.createEmployee(name, employeeRole, salary, contractType, contractStatus, id));
    }

    @Override
    public String toString() {
        return "userBean: " + username + " " + id + " " + name + " " + salary + " " + contractStatus + " " + contractType + " " + password;
    }

    public List getEmployeeList() {
        HandleEmployee employeeViewer = new HandleEmployee();
        return employeeViewer.getEmployeeList();
    }

    public void dismissEmployee() {
        System.out.println("The ID = " + id);
        HandleEmployee employeeDismisser = new HandleEmployee();
        employeeDismisser.dismiss(id);
    }

    public void editPassword() {
        System.out.println("Yo soy la B: " + passwordB);
        System.out.println("Yo soy la C: " + passwordC);
        if (passwordB.equals(passwordC)) {
            User userLoged = new User();
            userLoged = (User) httpServletRequest.getSession().getAttribute("sessionUser");
            HandleUser handleUser = new HandleUser();
            handleUser.editPassword(userLoged.getId(), passwordB);
            message = "Se ha cambiado la contraseña";
        } else {
            message = "No coinciden las contraseñas, intente de nuevo";
        }

    }

    public String getRespuesta() {
        if (username != null && password != null) {
            return "<p>Usuario: " + username + "<br/>Contraseña:" + password + "</p>";
        }
        return "";
    }

    public String login() {
        LoginController loginUser = new LoginController();
        if (loginUser.Login(username, password) != null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            User userLogin = loginUser.Login(username, password);
            httpServletRequest.getSession().setAttribute("sessionUser", userLogin);
            LoginController roleAuthenticator = new LoginController();
            message = roleAuthenticator.validateUser(userLogin.getId());
            return message;
        } else {
            message = "Verifique su usuario y contraseña.";
            return "index";
        }
    }

    public void loged() {
        User userLoged = new User();
        userLoged = (User) httpServletRequest.getSession().getAttribute("sessionUser");
        setId(userLoged.getId());
        setUsername(userLoged.getUsername());
        setPassword(userLoged.getPassword());
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "index";
    }
}
