package com.org.logistics.logship.provider.response;

import com.org.logistics.logship.provider.response.bo.ModifiedQualityCheckObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllQCResponse {

    private List<ModifiedQualityCheckObject> qualityChecks;
}
