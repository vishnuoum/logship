package com.logship.order.service.service;

import com.logship.order.service.controller.request.AddWeightRequest;
import com.logship.order.service.controller.request.CreateOrderRequest;
import com.logship.order.service.dto.OrderCreatedEventDTO;
import com.logship.order.service.dto.OrderDTO;
import com.logship.order.service.entity.Order;
import com.logship.order.service.exception.ExceptionManager;
import com.logship.order.service.exception.OrderServiceException;
import com.logship.order.service.logging.LogUtil;
import com.logship.order.service.mapper.OrderMapper;
import com.logship.order.service.producer.OrderCreatedEventProducer;
import com.logship.order.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderCreatedEventProducer orderCreatedEventProducer;

    public void createOrder(CreateOrderRequest request, String userId) {
        try {
            Order order = orderMapper.mapOrderFromRequest(request);
            order.setCustomerId(UUID.fromString(userId));
            orderRepository.save(order);
            OrderCreatedEventDTO eventDTO = orderMapper.mapToEventDTO(order);
            orderCreatedEventProducer.sendOrderCreatedEvent(eventDTO);
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while saving the order");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.ORDER_SAVE_ERROR);
        }
    }

    public OrderDTO getOrderDetails(UUID orderId) {
        try {
            Order order = orderRepository.findById(orderId).orElseThrow(() ->
                    ExceptionManager.throwException(ExceptionManager.ERRORCODE.ORDER_NOT_EXISTS_ERROR));
            return orderMapper.mapToDTO(order);
        } catch (OrderServiceException e) {
            throw e;
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while fetching the order");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.ORDER_NOT_EXISTS_ERROR);
        }
    }

    public List<OrderDTO> fetchAllOrders(String userId) {
        try {
            List<Order> orders = orderRepository.findAllByCustomerId(UUID.fromString(userId));
            return orderMapper.mapToDTO(orders);
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while fetching the order");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.ORDER_FETCH_ERROR);
        }
    }

    public void updateOrderWeight(AddWeightRequest addWeightRequest) {
        try {
            orderRepository.updateOrderWeight(addWeightRequest.getOrderId(), addWeightRequest.getWeight());
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while updating the weight of order");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.ORDER_WEIGHT_UPDATE_ERROR);
        }
    }
}
