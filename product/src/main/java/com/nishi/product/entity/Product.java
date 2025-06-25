package com.nishi.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    public Category categoryId;

    @Column(nullable = false)
    public String description;

    @Column(nullable = false)
    public Double price;

    @Column
    public LocalDateTime createdAt;

    @Column
    public LocalDateTime updatedAt;
}
