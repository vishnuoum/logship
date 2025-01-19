package com.org.logistics.logship.provider.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddQualityCheckRequest {

    private String qualityCheckName;
    private String description;
}
