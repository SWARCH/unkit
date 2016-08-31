/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.bean;

import businessLogic.controller.HandleCustomer;
import businessLogic.controller.HandleUser;
import businessLogic.controller.RoleAuthenticator;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author VangsPardz
 */
public class CustomerBean {
    
    private String message;
    
    /** <p>User properties.</p> */
    private String id;
    private String tradeName;
    private String type;

    public CustomerBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTradename() {
        return tradeName;
    }

    public void setTradename(String name) {
        this.tradeName = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
     public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void createAccount(){
       // HandleCustomer createAccount = new HandleCustomer();
        //this.setMessage(createAccount.createAccount(id, tradeName, type));
    }
    
    
}
