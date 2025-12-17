package com.kb.workflow.service;

import com.kb.common.constants.orders.OrderStatus;
import com.kb.common.dto.orders.OrdersDto;
import com.kb.common.dto.orders.OrdersResponseDto;
import com.kb.common.dto.products.ProductsResponseDto;
import com.kb.common.dto.users.UsersResponseDto;
import com.kb.common.dto.workflow.CreateOrderDto;
import com.kb.common.dto.workflow.StatusDto;
import com.kb.workflow.helper.OrdersClientHelper;
import com.kb.workflow.helper.ProductsClientHelper;
import com.kb.workflow.helper.UsersClientHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkflowService {
    private final OrdersClientHelper ordersClientHelper;
    private final ProductsClientHelper productsClientHelper;
    private final UsersClientHelper usersClientHelper;

    public OrdersResponseDto createOrder(final CreateOrderDto order) {
        final UsersResponseDto user = usersClientHelper.getUser(order);
        final ProductsResponseDto product = productsClientHelper.getProduct(order);

        final OrdersDto orderObj = new OrdersDto(user.id(), product.id(), order.quantity(), OrderStatus.CREATED);
        final OrdersResponseDto orderResponse = ordersClientHelper.placeOrder(orderObj);

        log.info("Order placed successfully, Order Id: {}", orderResponse.id());
        return orderResponse;
    }

    public OrdersResponseDto changeOrderStatus(final StatusDto status, final Integer id) {
        final OrdersResponseDto originalOrder = ordersClientHelper.getOrder(id);

        log.info("Successfully fetched order details: {}", originalOrder);

        final OrderStatus orderStatus = OrdersClientHelper.manageOrderStatus(status, originalOrder);

        final OrdersDto order = new OrdersDto(originalOrder.userId(), originalOrder.productId(), originalOrder.quantity(), orderStatus);
        final OrdersResponseDto updatedOrder = ordersClientHelper.updateOrderStatus(id, order);

        log.info("Order status changed successfully, Order status: {}", updatedOrder.status());
        return updatedOrder;
    }
}
