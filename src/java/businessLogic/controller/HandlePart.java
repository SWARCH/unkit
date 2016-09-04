/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic.controller;

import dataAcces.entity.Part;
import dataAccess.dao.PartDAO;

/**
 *
 * @author Mauricio
 */
public class HandlePart {
    
    public String createPart(String id, String name, String description, double cost) {
        Part newPart = new Part();
        newPart.setId(id);
        newPart.setName(name);
        newPart.setDescription(description);
        newPart.setCost(cost);
        
        PartDAO partDAO = new PartDAO();
        Part partE = partDAO.persist(newPart);
        
        if (partE != null) return "La parte ha sido registrada con éxito";
        return "Error en el registro de la parte";
    }
    
    public String eliminatePart(String id) {
        return "";
    }
    
    public String updatePart(String id, String name, String description, double cost) {
        return "";
    }
    
}
