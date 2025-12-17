package com.kb.workflow.helper;

import com.kb.common.constants.orders.OrderStatus;
import com.kb.common.dto.orders.OrdersDto;
import com.kb.common.dto.orders.OrdersResponseDto;
import com.kb.common.dto.workflow.StatusDto;
import com.kb.workflow.client.OrderServiceClient;
import com.kb.workflow.exceptions.WorkflowException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OrdersClientHelper {
    private static final Logger log = LoggerFactory.getLogger(OrdersClientHelper.class);
    private final OrderServiceClient orderServiceClient;

    public OrdersClientHelper(OrderServiceClient orderServiceClient) {
        this.orderServiceClient = orderServiceClient;
    }

    public OrdersResponseDto placeOrder(final OrdersDto order) {
        final ResponseEntity<OrdersResponseDto> response = orderServiceClient.saveOrder(order);

        if (response.getStatusCode() != HttpStatus.CREATED)
            throw new WorkflowException("Failed to place order");

        return response.getBody();
    }

    public OrdersResponseDto getOrder(final Integer id) {
        final ResponseEntity<OrdersResponseDto> response = orderServiceClient.getOrderById(id);

        if (response.getStatusCode() != HttpStatus.OK)
            throw new WorkflowException("Failed to get order details for Order id: " + id);

        return response.getBody();
    }

    public OrdersResponseDto updateOrderStatus(final Integer id, final OrdersDto order) {
        final ResponseEntity<OrdersResponseDto> response = orderServiceClient.updateOrder(order, id);

        if (response.getStatusCode() != HttpStatus.OK)
            throw new WorkflowException("Failed to get order details for Order id: " + id);

        return response.getBody();
    }

    public static OrderStatus manageOrderStatus(final StatusDto status, final OrdersResponseDto originalOrder) {
        final OrderStatus currentStatus = originalOrder.status();
        final OrderStatus newStatus = status.status();

        log.info("Current Status: {}, New Status: {}", currentStatus.name(), newStatus.name());

        if (currentStatus == OrderStatus.DELIVERED || currentStatus == OrderStatus.CANCELLED) {
            log.error("Order status cannot be changed to [{}] once DELIVERED or CANCELLED", newStatus.name());
            throw new WorkflowException("Failed to change order status to [" + newStatus.name() + "], Current status: [" + currentStatus.name() + "]");
        }

        return newStatus;
    }
}
