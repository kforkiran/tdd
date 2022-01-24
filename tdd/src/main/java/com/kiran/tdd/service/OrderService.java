package com.kiran.tdd.service;

import com.kiran.tdd.dto.Order;
import com.kiran.tdd.entities.OrderEntity;
import com.kiran.tdd.exceptions.OrderDetailsNotFoundException;
import com.kiran.tdd.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order getOrder(long id) {
        return orderRepository.findById(id)
                .map(order -> new Order(order.getId(), order.getCustomerEmail(), order.getCustomerAddress()))
                .orElseThrow(OrderDetailsNotFoundException::new);
    }

    public Order createOrder(Order order) {
        final OrderEntity newOrderEntity = orderRepository.save(toOrderEntity(order));
        return toOrderDto(newOrderEntity);
    }

    private Order toOrderDto(OrderEntity newOrderEntity) {
        return Order.builder()
                .id(newOrderEntity.getId())
                .customerEmail(newOrderEntity.getCustomerEmail())
                .customerAddress(newOrderEntity.getCustomerAddress())
                .build();
    }

    private OrderEntity toOrderEntity(Order order) {
        return OrderEntity.builder()
                .customerEmail(order.getCustomerEmail())
                .customerAddress(order.getCustomerAddress())
                .build();
    }
}
