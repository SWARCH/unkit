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
 * @author lorena
 */
@Entity
@Table(name = "Order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o"),
    @NamedQuery(name = "Order1.findById", query = "SELECT o FROM Order1 o WHERE o.order1PK.id = :id"),
    @NamedQuery(name = "Order1.findByOrderCost", query = "SELECT o FROM Order1 o WHERE o.orderCost = :orderCost"),
    @NamedQuery(name = "Order1.findByDate", query = "SELECT o FROM Order1 o WHERE o.date = :date"),
    @NamedQuery(name = "Order1.findByDeliveryDate", query = "SELECT o FROM Order1 o WHERE o.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "Order1.findByCustomerUserid", query = "SELECT o FROM Order1 o WHERE o.order1PK.customerUserid = :customerUserid")})
public class Order1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Order1PK order1PK;
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
    @JoinTable(name = "OrderPart", joinColumns = {
        @JoinColumn(name = "Order_id", referencedColumnName = "id"),
        @JoinColumn(name = "Order_Customer_User_id", referencedColumnName = "Customer_User_id")}, inverseJoinColumns = {
        @JoinColumn(name = "Part_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Part> partCollection;
    @JoinTable(name = "OrderVehicle", joinColumns = {
        @JoinColumn(name = "Order_id", referencedColumnName = "id"),
        @JoinColumn(name = "Order_Customer_User_id", referencedColumnName = "Customer_User_id")}, inverseJoinColumns = {
        @JoinColumn(name = "Vehicle_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Vehicle> vehicleCollection;
    @JoinColumn(name = "Customer_User_id", referencedColumnName = "User_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Customer customer;

    public Order1() {
    }

    public Order1(Order1PK order1PK) {
        this.order1PK = order1PK;
    }

    public Order1(Order1PK order1PK, Date date) {
        this.order1PK = order1PK;
        this.date = date;
    }

    public Order1(String id, String customerUserid) {
        this.order1PK = new Order1PK(id, customerUserid);
    }

    public Order1PK getOrder1PK() {
        return order1PK;
    }

    public void setOrder1PK(Order1PK order1PK) {
        this.order1PK = order1PK;
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
        hash += (order1PK != null ? order1PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1)) {
            return false;
        }
        Order1 other = (Order1) object;
        if ((this.order1PK == null && other.order1PK != null) || (this.order1PK != null && !this.order1PK.equals(other.order1PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dataAcces.entity.Order1[ order1PK=" + order1PK + " ]";
    }
    
}
