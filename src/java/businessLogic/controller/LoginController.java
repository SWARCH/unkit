/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic.controller;

import dataAcces.entity.User;
import dataAccess.dao.UserDAO;

/**
 *
 * @author lorena
 */
public class LoginController {
    
    public boolean Login(String id, String passW) {

        UserDAO usuarioDAO = new UserDAO();
        if (usuarioDAO.searchByUsername(id) != null) {
            User usuario = new User();
            usuario = usuarioDAO.searchByUsername(id);
            if (usuario.getPassword().equals(passW)) {
                return true;
            }
            return false;
        } else {
            return false;
        }   
    }    
}
