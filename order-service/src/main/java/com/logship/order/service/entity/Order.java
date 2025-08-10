package com.logship.order.service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    private UUID id;
    private UUID customerId;
    private String pickupAddress;
    private String dropAddress;
    private boolean isFragile;
    private String remarks;
}
