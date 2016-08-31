package businessLogic.controller;

import dataAccess.dao.UserDAO;
import dataAcces.entity.User;

/**
 *
 * @author arqsoft_2016_2
 */
public class HandleUser {
    
    public String createUser(String userName, String password, String id){
        User user = new User();

        user.setId(id);
        user.setPassword(password);
        user.setUsername(userName);

        UserDAO UserDAO = new UserDAO();
        User userE = UserDAO.persist(user);
        if (userE != null)
            return "la cuenta ha sido creada, su número de cuenta es " + user.getId() + ".";
        else
            return "la cuenta no pudo ser creada.";  
    }
    
}
