package businessLogic.controller;

import dataAcces.entity.Customer;
import dataAccess.dao.CustomerDAO;


/**
 *
 * @author arqsoft_2016_2
 */
public class HandleCustomer {
    
    public String createAccount(String id, String tradeName, String type){
        Customer account = new Customer();

        account.setUserid(id);
        account.setTradeName(tradeName);
        account.setType(type);

        CustomerDAO CustomerDAO = new CustomerDAO();
        Customer cx = CustomerDAO.persist(account);
        
        if (cx != null) {
            return "la cuenta cx ha sido creada, su número de cuenta es " + account.getUserid() + ".";
        } else {
            return "la cuenta no pudo ser creada.";
        }
    }
}
