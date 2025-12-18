package com.kb.orders.controller.v1;

import com.kb.common.dto.orders.OrdersDto;
import com.kb.common.dto.orders.OrdersResponseDto;
import com.kb.orders.controller.OrdersController;
import com.kb.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersControllerImpl implements OrdersController {
    private final OrdersService ordersService;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrdersResponseDto>> getOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.getAllOrders());
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersResponseDto> getOrderById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.getOrderById(id));
    }

    @Override
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersResponseDto> saveOrder(@RequestBody OrdersDto order) {
        order.validate();
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.saveOrder(order));
    }

    @Override
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersResponseDto> updateOrder(@RequestBody OrdersDto order, @PathVariable("id") Integer id) {
        order.validate();
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.updateOrderById(order, id));
    }

    @Override
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id) {
        ordersService.deleteOrderById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
