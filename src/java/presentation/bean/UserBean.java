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

    public UserBean() {
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
        HandleAccount createAccount = new HandleAccount();
        message = createAccount.createAccount(username, password, id);
    }
    
}
