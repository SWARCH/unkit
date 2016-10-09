/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic.controller;

import dataAcces.entity.Order1;
import dataAcces.entity.Order1PK;
import dataAcces.entity.Vehicle;
import dataAccess.dao.OrderSaleDAO;
import dataAccess.dao.VehicleDAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author VangsPardz
 */
public class HandleVehicle {

    public final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    public String Now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public List<Vehicle> getProductList() {
        VehicleDAO v = new VehicleDAO();
        return v.findAll();
    }

    public String order(List<Vehicle> v) {
        Order1 order = new Order1();
        double cost = 0.0;
        for (Vehicle x : v) {
            cost += x.getCost();
        }
        order.setOrderCost(cost);
        order.setDate(null);
        order.setDeliveryDate(null);
        order.setOrder1PK(null);

        OrderSaleDAO os = new OrderSaleDAO();
        Order1 oss = os.persist(order);
        if (oss != null) {
            return "Compra exitosa " + order.getOrderCost() + "  ss " ;
        } else {
            return "la cuenta no pudo ser creada.";
        }
    }

    public String createVehicle(String id, String trademark, int model, 
            String description, String color, double cost) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setTrademark(trademark);
        vehicle.setModel(model);
        vehicle.setDescription(description);
        vehicle.setColor(color);
        vehicle.setCost(cost);
        
        VehicleDAO vehicleDAO = new VehicleDAO();
        Vehicle ex = vehicleDAO.persist(vehicle);
        
        if (ex != null) return "El vehiculo ha sido registrado de manera correcta";
        return "Error al registrar el vehiclulo, consulte a los ingenieros";
    }
    
    public String updateVehicle(String id, String trademark,int model,String color, String description, double cost ) {
        
        System.out.println("Handle Vehicle: " + id);
        
        VehicleDAO vehicleDAO = new VehicleDAO();
        Vehicle candidate = vehicleDAO.searchByID(id);
        
        candidate.setTrademark(trademark);
        candidate.setModel(model);
        candidate.setColor(color);
        candidate.setDescription(description);
        candidate.setCost(cost);
    
        if (candidate == null) {
            System.out.println("What the hell");
            
            return "Error al actualizar";
        }
        
        vehicleDAO.update(candidate);
        return "El vehículo " + id + "se actualizó correctamente.";
        
    }

}
