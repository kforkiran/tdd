package com.kiran.tdd.service;

import com.kiran.tdd.dto.Order;
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
                .orElseThrow(RuntimeException::new);
    }
}
