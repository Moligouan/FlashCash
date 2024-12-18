package com.nnk.springboot.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FlashCashAccount {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    private Double totalAmount;
    private Double flashCashAmount;
}
