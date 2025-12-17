package com.kb.orders.controller;

import com.kb.common.dto.orders.OrdersDto;
import com.kb.common.dto.orders.OrdersResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OrdersController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrdersResponseDto>> getOrders();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrdersResponseDto> getOrderById(@PathVariable("id") Integer id);

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrdersResponseDto> saveOrder(@RequestBody OrdersDto order);

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrdersResponseDto> updateOrder(@RequestBody OrdersDto order, @PathVariable("id") Integer id);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id);
}
