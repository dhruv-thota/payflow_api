package com.payflow.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String upiId;
    private String phoneNumber;
    private Double balance;

    public User() {
    }

    public User(String name, String upiId, String phoneNumber, Double balance) {
        this.name = name;
        this.upiId = upiId;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public User(Long id, String name, String upiId, String phoneNumber, Double balance) {
        this.id = id;
        this.name = name;
        this.upiId = upiId;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
