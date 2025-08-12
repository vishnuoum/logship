package com.logship.tracker.service.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StatusHistoryEntry {

    private String status;
    private Date timestamp;
}
