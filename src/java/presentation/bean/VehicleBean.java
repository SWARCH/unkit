/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.bean;

import businessLogic.controller.HandleVehicle;
import dataAcces.entity.Vehicle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlInputHidden;

/**
 *
 * @author VangsPardz
 */

@ManagedBean
@ViewScoped
public class VehicleBean {

    private String message, id, trademark, description, color;
    private double cost;
    private int model;
    private HtmlDataTable dataTable;
    public  Vehicle product = new Vehicle();
    private HtmlInputHidden productId = new HtmlInputHidden();
    private List<Vehicle> cart = new ArrayList<Vehicle>(); 

    public VehicleBean(){
        
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    
    public List getProductList(){
            HandleVehicle v = new HandleVehicle();
            return v.getProductList();
        
    }
    
    public Vehicle getSpecificProduct(){
       // product = (Vehicle) dataTable.getRowData();
        productId.setValue(product.getId());
        
        return null;
    }
    
    public String addToCart(){
        product = (Vehicle) dataTable.getRowData();
        cart.add(product);
        return "update";
    }
    
    public String makeOrder(){
        HandleVehicle v = new HandleVehicle();
        this.setMessage(v.order(cart));
        return message;
    }
    
    public void createVehicle() {
        HandleVehicle vehicleCreator = new HandleVehicle();
        this.setMessage(vehicleCreator.createVehicle(id, trademark, model, description, color, cost));
    }
    
}
