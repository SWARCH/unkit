/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAcces.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author VangsPardz
 */
@Entity
@Table(name = "order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderSale.findAll", query = "SELECT o FROM OrderSale o"),
    @NamedQuery(name = "OrderSale.findById", query = "SELECT o FROM OrderSale o WHERE o.orderSalePK.id = :id"),
    @NamedQuery(name = "OrderSale.findByOrderCost", query = "SELECT o FROM OrderSale o WHERE o.orderCost = :orderCost"),
    @NamedQuery(name = "OrderSale.findByDate", query = "SELECT o FROM OrderSale o WHERE o.date = :date"),
    @NamedQuery(name = "OrderSale.findByDeliveryDate", query = "SELECT o FROM OrderSale o WHERE o.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "OrderSale.findByCustomerUserid", query = "SELECT o FROM OrderSale o WHERE o.orderSalePK.customerUserid = :customerUserid")})
public class OrderSale implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderSalePK orderSalePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "orderCost")
    private Double orderCost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "deliveryDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;
    @JoinTable(name = "orderpart", joinColumns = {
        @JoinColumn(name = "Order_id", referencedColumnName = "id"),
        @JoinColumn(name = "Order_Customer_User_id", referencedColumnName = "Customer_User_id")}, inverseJoinColumns = {
        @JoinColumn(name = "Part_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Part> partCollection;
    @JoinTable(name = "ordervehicle", joinColumns = {
        @JoinColumn(name = "Order_id", referencedColumnName = "id"),
        @JoinColumn(name = "Order_Customer_User_id", referencedColumnName = "Customer_User_id")}, inverseJoinColumns = {
        @JoinColumn(name = "Vehicle_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Vehicle> vehicleCollection;
    @JoinColumn(name = "Customer_User_id", referencedColumnName = "User_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Customer customer;

    public OrderSale() {
    }

    public OrderSale(OrderSalePK orderSalePK) {
        this.orderSalePK = orderSalePK;
    }

    public OrderSale(OrderSalePK orderSalePK, Date date) {
        this.orderSalePK = orderSalePK;
        this.date = date;
    }

    public OrderSale(String id, String customerUserid) {
        this.orderSalePK = new OrderSalePK(id, customerUserid);
    }

    public OrderSalePK getOrderSalePK() {
        return orderSalePK;
    }

    public void setOrderSalePK(OrderSalePK orderSalePK) {
        this.orderSalePK = orderSalePK;
    }

    public Double getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(Double orderCost) {
        this.orderCost = orderCost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @XmlTransient
    public Collection<Part> getPartCollection() {
        return partCollection;
    }

    public void setPartCollection(Collection<Part> partCollection) {
        this.partCollection = partCollection;
    }

    @XmlTransient
    public Collection<Vehicle> getVehicleCollection() {
        return vehicleCollection;
    }

    public void setVehicleCollection(Collection<Vehicle> vehicleCollection) {
        this.vehicleCollection = vehicleCollection;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderSalePK != null ? orderSalePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderSale)) {
            return false;
        }
        OrderSale other = (OrderSale) object;
        if ((this.orderSalePK == null && other.orderSalePK != null) || (this.orderSalePK != null && !this.orderSalePK.equals(other.orderSalePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dataAcces.entity.OrderSale[ orderSalePK=" + orderSalePK + " ]";
    }
    
}
