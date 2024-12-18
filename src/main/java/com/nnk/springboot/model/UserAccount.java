package com.nnk.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer accountId;

    private Double amount;
    private String iban;

    public UserAccount plus(double amount) {
        this.amount += amount;
        return this;
    }

    public UserAccount minus(double amount) {
        this.amount -= amount;
        return this;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
