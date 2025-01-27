package com.org.logistics.logship.mappers.mapstruct;

import com.org.logistics.logship.dto.QualityCheckObject;
import com.org.logistics.logship.provider.request.AddQualityCheckRequest;
import com.org.logistics.logship.provider.response.AddQualityCheckResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QualityCheckStructMapper {

    QualityCheckObject requestToQualityCheckObject(AddQualityCheckRequest addQualityCheckRequest);

    @Mapping(target = "qualityCheckId",
            expression = "java(com.org.logistics.logship.util.CommonUtil.appendPrefixToId(qualityCheckObject.getQualityCheckId(), com.org.logistics.logship.constants.Constants.QUALITY_CHECK_PREFIX))")
    AddQualityCheckResponse qualityCheckObjectToAddQualityResponse(QualityCheckObject qualityCheckObject);
}
