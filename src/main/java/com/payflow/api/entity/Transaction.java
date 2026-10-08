package com.payflow.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderUpiId;
    private String receiverUpiId;
    private Double amount;

    public Transaction() {
    }

    public Transaction(String senderUpiId, String receiverUpiId, Double amount) {
        this.senderUpiId = senderUpiId;
        this.receiverUpiId = receiverUpiId;
        this.amount = amount;
    }

    public Transaction(Long id, String senderUpiId, String receiverUpiId, Double amount) {
        this.id = id;
        this.senderUpiId = senderUpiId;
        this.receiverUpiId = receiverUpiId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderUpiId() {
        return senderUpiId;
    }

    public void setSenderUpiId(String senderUpiId) {
        this.senderUpiId = senderUpiId;
    }

    public String getReceiverUpiId() {
        return receiverUpiId;
    }

    public void setReceiverUpiId(String receiverUpiId) {
        this.receiverUpiId = receiverUpiId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
