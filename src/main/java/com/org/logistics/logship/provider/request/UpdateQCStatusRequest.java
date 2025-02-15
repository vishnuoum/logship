package com.org.logistics.logship.provider.request;

import com.org.logistics.logship.provider.request.bo.QualityStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateQCStatusRequest {

    @NotEmpty(message = "Order id should not be null or empty")
    @Pattern(regexp = "^O\\d+$", message = "Order Id pattern not matched")
    private String orderId;

    @NotEmpty(message = "Handler id should not be null or empty")
    @Pattern(regexp = "^H\\d+$", message = "Handler Id pattern not matched")
    private String handlerId;

    @Valid
    @NotEmpty(message = "List of quality checks should not be null or empty")
    private List<QualityStatus> qualityStatusList;
}
