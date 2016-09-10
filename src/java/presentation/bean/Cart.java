/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.bean;

import dataAcces.entity.Vehicle;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.*;

/**
 *
 * @author Mauricio
 */
@Named(value = "cart")
@ManagedBean
@ViewScoped
public class Cart implements Serializable {
    
    private List<Vehicle> vehicles = new ArrayList<>();

    /**
     * Creates a new instance of Cart
     */
    public Cart() {
    }
    
    public void add(Vehicle vehicle){ 
        vehicles.add(vehicle);
    }
    
    public void remove(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }
    
    public int getCartCount() {
        return vehicles.size();
    }
    
}
