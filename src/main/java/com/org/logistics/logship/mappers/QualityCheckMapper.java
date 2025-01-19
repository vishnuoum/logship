package com.org.logistics.logship.mappers;

import com.org.logistics.logship.provider.dto.request.QualityCheckDtoRequest;
import com.org.logistics.logship.provider.dto.response.QualityCheckDtoResponse;
import com.org.logistics.logship.provider.request.AddQualityCheckRequest;
import com.org.logistics.logship.provider.response.AddQualityCheckResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QualityCheckMapper {

    QualityCheckDtoRequest mapRequestToDtoRequest(AddQualityCheckRequest addQualityCheckRequest);

    AddQualityCheckResponse mapDtoResponseToResponse(QualityCheckDtoResponse qualityCheckDaoResponse);
}
