package com.org.logistics.logship.mappers;

import com.org.logistics.logship.provider.dto.request.PlaceOrderDtoRequest;
import com.org.logistics.logship.provider.dto.response.PlaceOrderDtoResponse;
import com.org.logistics.logship.provider.request.PlaceOrderRequest;
import com.org.logistics.logship.provider.response.PlaceOrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    PlaceOrderDtoRequest mapOrderRequestToOrderDtoRequest(PlaceOrderRequest placeOrderRequest);

    PlaceOrderResponse mapOrderDtoResponseToOrderResponse(PlaceOrderDtoResponse placeOrderDaoResponse);

}
