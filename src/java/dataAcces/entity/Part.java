/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAcces.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author VangsPardz
 */
@Entity
@Table(name = "part")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Part.findAll", query = "SELECT p FROM Part p"),
    @NamedQuery(name = "Part.findById", query = "SELECT p FROM Part p WHERE p.id = :id"),
    @NamedQuery(name = "Part.findByName", query = "SELECT p FROM Part p WHERE p.name = :name"),
    @NamedQuery(name = "Part.findByDescription", query = "SELECT p FROM Part p WHERE p.description = :description"),
    @NamedQuery(name = "Part.findByCost", query = "SELECT p FROM Part p WHERE p.cost = :cost")})
public class Part implements Serializable {

    @ManyToMany(mappedBy = "partCollection")
    private Collection<OrderSale> orderSaleCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 300)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost")
    private double cost;

    public Part() {
    }

    public Part(String id) {
        this.id = id;
    }

    public Part(String id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Part)) {
            return false;
        }
        Part other = (Part) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dataAcces.entity.Part[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<OrderSale> getOrderSaleCollection() {
        return orderSaleCollection;
    }

    public void setOrderSaleCollection(Collection<OrderSale> orderSaleCollection) {
        this.orderSaleCollection = orderSaleCollection;
    }
    
}
