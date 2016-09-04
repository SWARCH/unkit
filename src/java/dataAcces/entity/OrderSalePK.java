/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAcces.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author VangsPardz
 */
@Embeddable
public class OrderSalePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Customer_User_id")
    private String customerUserid;

    public OrderSalePK() {
    }

    public OrderSalePK(String id, String customerUserid) {
        this.id = id;
        this.customerUserid = customerUserid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerUserid() {
        return customerUserid;
    }

    public void setCustomerUserid(String customerUserid) {
        this.customerUserid = customerUserid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        hash += (customerUserid != null ? customerUserid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderSalePK)) {
            return false;
        }
        OrderSalePK other = (OrderSalePK) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if ((this.customerUserid == null && other.customerUserid != null) || (this.customerUserid != null && !this.customerUserid.equals(other.customerUserid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dataAcces.entity.OrderSalePK[ id=" + id + ", customerUserid=" + customerUserid + " ]";
    }
    
}
