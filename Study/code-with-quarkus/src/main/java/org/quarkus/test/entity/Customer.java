package org.quarkus.test.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends PanacheEntity {

    @Column(length = 40, unique = true)
    public String name;

    @Column(length = 40, unique = true)
    public String custId;

    @Column(length = 100)
    public String region;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(double loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Column(length = 10)
    public double loyaltyPoints;

    public Customer(){
    }

    public Customer(Customer customer){
        this.name =  customer.name;
        this.custId = customer.custId;
        this.loyaltyPoints = customer.loyaltyPoints;
        this.region = customer.region;
    }
}
