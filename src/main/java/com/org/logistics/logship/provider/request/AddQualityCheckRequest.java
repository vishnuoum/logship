package com.org.logistics.logship.provider.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddQualityCheckRequest {

    @NotEmpty(message = "Quality check name cannot be null or empty")
    private String qualityCheckName;

    @NotEmpty(message = "Quality check description cannot be null or empty")
    private String description;
}
