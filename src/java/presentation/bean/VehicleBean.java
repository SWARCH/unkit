/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.bean;

import businessLogic.controller.HandleVehicle;
import dataAcces.entity.Vehicle;
import dataAccess.dao.VehicleDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.FacesContext;

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
    public Vehicle product = new Vehicle();
    private HtmlInputHidden productId = new HtmlInputHidden();
    private List<Vehicle> cart = new ArrayList<Vehicle>();
    
    @ManagedProperty( value = "#{cart}" )
    Cart myCart;

    public VehicleBean() {

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

    public List getProductList() {
        HandleVehicle v = new HandleVehicle();
        return v.getProductList();
    }

    public Vehicle getSpecificProduct() {
        productId.setValue(product.getId());

        return null;
    }

    public String makeOrder() {
        HandleVehicle v = new HandleVehicle();
        this.setMessage(v.order(cart));
        return message;
    }

    public void createVehicle() {
        HandleVehicle vehicleCreator = new HandleVehicle();
        this.setMessage(vehicleCreator.createVehicle(id, trademark, model, description, color, cost));
    }

    public String getQuery() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("query");
    }
    
    public void checkIfQueryExists() {
        VehicleDAO vehicleDAO = new VehicleDAO();
        if (vehicleDAO.checkIfQueryExists(getQuery()) == 0) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(VehicleBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else System.out.println("it actually exist!");
    }
    
    public Vehicle getSingleVehicle() {
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.returnVehicle(getQuery());
    }
    
    public void addToCart() {
        VehicleDAO vehicleDAO = new VehicleDAO();
        String query = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("query");
        myCart.add(vehicleDAO.returnVehicle(query));
    }

    public Cart getMyCart() {
        return myCart;
    }

    public void setMyCart(Cart myCart) {
        this.myCart = myCart;
    }

}
