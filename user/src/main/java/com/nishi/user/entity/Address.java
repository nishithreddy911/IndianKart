package com.nishi.user.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String street;

    @Column(nullable = false)
    public String city;

    @Column(nullable = false)
    public String state;

    @Column(nullable = false)
    public String zip;

    @Column(nullable = false)
    public String country;

    @Column(nullable = false)
    public String phone;

    @Column(name = "created_by")
    public String createdBy;

    @Column(name = "created_date")
    public LocalDateTime createdDate;

    @Column(name = "updated_by")
    public String updatedBy;

    @Column(name = "updated_date")
    public LocalDateTime updatedDate;
}
