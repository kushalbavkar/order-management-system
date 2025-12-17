package com.kb.products.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Clock;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "in_stock")
    private Boolean inStock;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now(Clock.systemUTC());
    }
}
