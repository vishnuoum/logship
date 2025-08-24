package com.logship.shipment.service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table
@Entity
@Getter
@Setter
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JdbcTypeCode(Types.VARCHAR)
    private UUID driverId;
    private Long fromWarehouseId;
    private Long toWarehouseId;
    private Date createdDate = new Date();
    private Date startDate;
    private Date endDate;
    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ShipmentOrders> shipmentOrders;
}
