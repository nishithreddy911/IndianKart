package com.nishi.user.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String name;

    @Column(name = "mobile_number", nullable = false)
    public String mobileNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Address address;

    @Column(name = "created_by")
    public String createdBy;

    @Column(name = "created_date")
    public LocalDateTime createdDate;

    @Column(name = "updated_by")
    public String updatedBy;

    @Column(name = "updated_date")
    public LocalDateTime updatedDate;
}
