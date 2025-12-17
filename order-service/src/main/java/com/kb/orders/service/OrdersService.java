package com.kb.orders.service;

import com.kb.common.dto.orders.OrdersDto;
import com.kb.common.dto.orders.OrdersResponseDto;
import com.kb.orders.exceptions.OrdersException;
import com.kb.orders.repository.OrdersRepository;
import com.kb.orders.repository.mapper.EntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersService(final OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<OrdersResponseDto> getAllOrders() {
        log.info("Getting all orders");
        return ordersRepository
                .findAll()
                .stream()
                .map(EntityMapper::entityToDto)
                .toList();
    }

    public OrdersResponseDto getOrderById(final int id) {
        log.info("Getting order by id: {}", id);
        return ordersRepository
                .findById(id)
                .map(EntityMapper::entityToDto)
                .orElseThrow(() -> new OrdersException("Missing order with id: " + id));
    }

    public OrdersResponseDto saveOrder(final OrdersDto order) {
        log.info("Saving order: {}", order);
        return Optional
                .of(EntityMapper.dtoToEntity(order))
                .map(ordersRepository::save)
                .map(EntityMapper::entityToDto)
                .orElseThrow(() -> new OrdersException("Unable to save order: " + order));
    }

    public OrdersResponseDto updateOrderById(final OrdersDto order, final int id) {
        log.info("Updating order with id: {}", id);
        return ordersRepository
                .findById(id)
                .map(entity -> EntityMapper.updateEntity(entity, order))
                .map(ordersRepository::save)
                .map(EntityMapper::entityToDto)
                .orElseThrow(() -> new OrdersException("Failed to update order with id: " + id));
    }

    public void deleteOrderById(final int id) {
        log.info("Deleting order with id: {}", id);
        ordersRepository
                .deleteById(id);
    }
}
