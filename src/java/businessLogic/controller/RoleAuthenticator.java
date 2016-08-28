/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic.controller;

import dataAcces.entity.User;
import dataAccess.dao.UserDAO;

/**
 * A controller class to find the role of the user.
 * @author Mauricio
 */
public class RoleAuthenticator {

    public RoleAuthenticator() {
    }
    
    public String validateUser(String id, String username, String password) {
        User user = null;
        UserDAO userDAO = new UserDAO();
        user = userDAO.searchByID(id);
        
        if (user != null) {
            if (!password.equals(user.getPassword())) {
                return "Incorrect password";
            } else {
                return "Valid user";
            }
        } else {
            return "Can't find the user";
        }
    }
    
}
