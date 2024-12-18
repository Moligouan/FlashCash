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
}
