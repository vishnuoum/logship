package com.org.logistics.logship.mappers.mapstruct;

import com.org.logistics.logship.constants.Constants;
import com.org.logistics.logship.dto.QualityCheckObject;
import com.org.logistics.logship.provider.request.AddQualityCheckRequest;
import com.org.logistics.logship.provider.response.AddQualityCheckResponse;
import com.org.logistics.logship.provider.response.bo.ModifiedQualityCheckObject;
import com.org.logistics.logship.util.CommonUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QualityCheckStructMapper {

    QualityCheckObject requestToQualityCheckObject(AddQualityCheckRequest addQualityCheckRequest);

    @Mapping(target = "qualityCheckId",
            expression = "java(com.org.logistics.logship.util.CommonUtil.appendPrefixToId(qualityCheckObject.getQualityCheckId(), com.org.logistics.logship.constants.Constants.QUALITY_CHECK_PREFIX))")
    AddQualityCheckResponse qualityCheckObjectToAddQualityResponse(QualityCheckObject qualityCheckObject);

    List<ModifiedQualityCheckObject> getModifiedQualityCheckObject(List<QualityCheckObject> qualityCheckObjects);

    default ModifiedQualityCheckObject getModifiedQualityCheckObject(QualityCheckObject qualityCheckObject) {
        ModifiedQualityCheckObject modifiedQualityCheckObject = new ModifiedQualityCheckObject();
        modifiedQualityCheckObject.setQualityCheckId(CommonUtil.appendPrefixToId(qualityCheckObject.getQualityCheckId(), Constants.QUALITY_CHECK_PREFIX));
        modifiedQualityCheckObject.setQualityCheckName(qualityCheckObject.getQualityCheckName());
        modifiedQualityCheckObject.setDescription(qualityCheckObject.getDescription());
        return modifiedQualityCheckObject;
    }
}
