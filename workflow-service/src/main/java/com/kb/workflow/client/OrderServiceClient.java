package com.kb.workflow.client;

import com.kb.common.dto.orders.OrdersDto;
import com.kb.common.dto.orders.OrdersResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "order-service",
        url = "${services.order-service.url}"
)
public interface OrderServiceClient {
    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrdersResponseDto>> getOrders();

    @GetMapping(value = "/orders/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrdersResponseDto> getOrderById(@PathVariable("id") Integer id);

    @PostMapping(value = "/orders/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrdersResponseDto> saveOrder(@RequestBody OrdersDto order);

    @PutMapping(value = "/orders/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrdersResponseDto> updateOrder(@RequestBody OrdersDto order, @PathVariable("id") Integer id);

    @DeleteMapping(value = "/orders/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id);
}
