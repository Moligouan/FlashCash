package com.nnk.springboot.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Link {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer ID;
    @ManyToOne
    User user1;
    @ManyToOne
    User user2;

    public Link() {
    }

    public Link(Integer ID, User user1, User user2) {
        this.ID = ID;
        this.user1 = user1;
        this.user2 = user2;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
