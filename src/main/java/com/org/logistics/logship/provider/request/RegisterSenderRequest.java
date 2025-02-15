package com.org.logistics.logship.provider.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterSenderRequest {

    @NotEmpty(message = "Sender phone should not be null or empty")
    @Pattern(regexp = "^\\d+$", message = "Sender phone pattern not matched")
    private String senderPhone;
    private String senderMail;
}
