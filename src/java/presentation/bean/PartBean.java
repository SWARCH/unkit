/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.bean;

import businessLogic.controller.HandlePart;
import dataAcces.entity.Part;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * <p>A simple managed bean to mediate between the front-end and 
 * the business logic of parts
 * @author Mauricio
 */
@ManagedBean
@ViewScoped
public class PartBean {
    private String message;
    
    /** <p>Part properties.</p> */
    private String id;
    private String name;
    private String description;
    private double cost;

    public PartBean(String id, String name, String description, double cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public PartBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void createPart() {
        HandlePart partCreator = new HandlePart();
        this.setMessage(partCreator.createPart(id, name, description, cost));
    }
    
    public void eliminatePart() {
        HandlePart partEliminator = new HandlePart();
        this.setMessage(partEliminator.eliminatePart(id));
    }
    
    public void updatePartName() {
        System.out.println(this);
        HandlePart partUpdater = new HandlePart();
        this.setMessage(partUpdater.updateName(id, name));
        
    }
    
    public Part getPartById(String id) {
        System.out.println(this);
        HandlePart partBrowser = new HandlePart();
        return partBrowser.getPartById(id);
    }

    @Override
    public String toString() {
        return id + " " + name+ " " + description + " " + cost;
    }
    
    
    
}
