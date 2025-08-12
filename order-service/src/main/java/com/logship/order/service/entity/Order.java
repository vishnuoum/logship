package com.logship.order.service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;
    @JdbcTypeCode(Types.VARCHAR)
    private UUID customerId;
    private String pickupAddress;
    private String dropAddress;
    private boolean isFragile;
    private String remarks;
    private Date createdDate = new Date();
}
