package com.org.logistics.logship.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class SenderDetails {

    private Integer senderId;
    private String senderPhone;
    private String senderMail;
    private Date createdDate;
}
