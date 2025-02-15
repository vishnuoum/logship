package com.org.logistics.logship.provider.request.bo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QualityStatus {

    @NotEmpty(message = "Quality Check id should not be null or empty")
    @Pattern(regexp = "^QC\\d+$", message = "Quality Check Id pattern not matched")
    private String qualityCheckId;

    @NotEmpty(message = "Status should not be null or empty")
    private String status;
}
