package com.nnk.springboot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Entity
public class FlashCashAccount {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    private Double totalAmount;
    private Double flashCashAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getFlashCashAmount() {
        return flashCashAmount;
    }

    public void setFlashCashAmount(Double flashCashAmount) {
        this.flashCashAmount = BigDecimal.valueOf(flashCashAmount)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();;
    }
}
