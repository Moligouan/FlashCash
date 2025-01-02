package com.nnk.springboot.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    private LocalDateTime date;
    @ManyToOne
    private User from;
    @ManyToOne
    private User to;
    private Double amountBeforeFee;
    private Double amountAfterFee;

    public Transfer() {
    }

    public Transfer(Integer id, LocalDateTime date, User from, User to, Double amountBeforeFee, Double amountAfterFee) {
        this.id = id;
        this.date = date;
        this.from = from;
        this.to = to;
        this.amountBeforeFee = amountBeforeFee;
        this.amountAfterFee = amountAfterFee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public Double getAmountBeforeFee() {
        return amountBeforeFee;
    }

    public void setAmountBeforeFee(Double amountBeforeFee) {
        this.amountBeforeFee = amountBeforeFee;
    }

    public Double getAmountAfterFee() {
        return amountAfterFee;
    }

    public void setAmountAfterFee(Double amountAfterFee) {
        this.amountAfterFee = amountAfterFee;
    }
}
