package com.videostore.customer.data.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "registration_date")
    private String registrationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRegistrationDate() { return registrationDate; }

    public void setRegistrationDate(String registrationDate){ this.registrationDate = registrationDate; }
}
