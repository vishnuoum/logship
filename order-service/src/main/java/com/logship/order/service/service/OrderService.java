package com.logship.order.service.service;

import com.logship.order.service.controller.request.CreateOrderRequest;
import com.logship.order.service.dto.OrderDTO;
import com.logship.order.service.entity.Order;
import com.logship.order.service.entity.User;
import com.logship.order.service.exception.ExceptionManager;
import com.logship.order.service.logging.LogUtil;
import com.logship.order.service.mapper.OrderMapper;
import com.logship.order.service.repository.OrderRepository;
import com.logship.order.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public void createOrder(CreateOrderRequest request) {
        try {
            orderRepository.save(orderMapper.mapOrderFromRequest(request));
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while saving the order");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.ORDER_SAVE_ERROR);
        }
    }

    public OrderDTO getOrderDetails(UUID orderId) {
        try {
            Order order = orderRepository.findById(orderId).orElseThrow(() ->
                    ExceptionManager.throwException(ExceptionManager.ERRORCODE.ORDER_FETCH_ERROR));
            return orderMapper.mapToDTO(order);
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while fetching the order");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.ORDER_FETCH_ERROR);
        }
    }

    public List<OrderDTO> fetchAllOrders(String name) {
        try {
            User user = userRepository.findByUsername(name).orElseThrow(() -> ExceptionManager.throwException(ExceptionManager.ERRORCODE.USER_NOT_FOUND_ERROR));
            List<Order> orders = orderRepository.findAllByCustomerId(user.getId());
            return orderMapper.mapToDTO(orders);
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while fetching the order");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.ORDER_FETCH_ERROR);
        }
    }
}
