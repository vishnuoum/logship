package com.org.logistics.logship.provider.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterSenderRequest {

    private String senderPhone;
    private String senderMail;
}
