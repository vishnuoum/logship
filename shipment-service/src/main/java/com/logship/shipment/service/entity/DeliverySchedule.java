package com.logship.shipment.service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.Date;
import java.util.UUID;

@Table
@Entity
@Getter
@Setter
public class DeliverySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JdbcTypeCode(Types.VARCHAR)
    private UUID driverId;
    @JdbcTypeCode(Types.VARCHAR)
    private UUID orderId;
    private String address;
    private String pinCode;
    private Date createdDate = new Date();
    private Date endDate;
}
