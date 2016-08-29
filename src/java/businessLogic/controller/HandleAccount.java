package businessLogic.controller;

import dataAccess.dao.UserDAO;
import dataAcces.entity.User;

/**
 *
 * @author arqsoft_2016_2
 */
public class HandleAccount {
    
    public String createAccount(String userName, String password, String id){
        User account = new User();

        account.setId(id);
        account.setPassword(password);
        account.setUsername(userName);

        UserDAO UserDAO = new UserDAO();
        User userE = UserDAO.persist(account);
        if (userE != null)
            return "la cuenta ha sido creada, su número de cuenta es " + account.getId() + ".";
        else
            return "la cuenta no pudo ser creada.";  
    }
    
}
