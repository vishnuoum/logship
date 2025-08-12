package com.logship.tracker.service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order_status")
@Getter
@Setter
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JdbcTypeCode(Types.VARCHAR)
    private UUID orderId;
    @JdbcTypeCode(Types.VARCHAR)
    private UUID warehouseId;

    @Enumerated(EnumType.STRING)
    private Status status;
    private Date timestamp;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<StatusHistoryEntry> history = new ArrayList<>();
}
