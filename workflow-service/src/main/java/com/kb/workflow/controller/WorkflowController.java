package com.kb.workflow.controller;

import com.kb.common.dto.orders.OrdersResponseDto;
import com.kb.common.dto.workflow.CreateOrderDto;
import com.kb.common.dto.workflow.StatusDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface WorkflowController {
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrdersResponseDto> createOrder(@RequestBody CreateOrderDto order);

    @PutMapping(value = "/{id}/change-status", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrdersResponseDto> changeOrderStatus(@RequestBody StatusDto order, @PathVariable("id") Integer id);
}
