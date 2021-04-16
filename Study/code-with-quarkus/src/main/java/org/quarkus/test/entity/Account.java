package org.quarkus.test.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account extends PanacheEntityBase {

    @Column(length = 40, unique = true)
    public String name;

    @Column(length = 40)
    @Id
    public long id;

    @Column (length = 40)
    public String accId;

    @Column(length = 100)
    public String region;

    @Column(length = 10)
    public double loyaltyPoints;
}
