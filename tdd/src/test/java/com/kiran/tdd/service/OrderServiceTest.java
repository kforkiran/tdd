package com.kiran.tdd.service;

import com.kiran.tdd.dto.Order;
import com.kiran.tdd.entities.OrderEntity;
import com.kiran.tdd.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void getOrderDetails_shouldReturn_OrderDetails() {
        OrderEntity orderEntity = new OrderEntity(1l, "pawar_kiran@live.in", "asdsf");
        given(orderRepository.findById(1l)).willReturn(Optional.of(orderEntity));
        Order orderResponse = orderService.getOrder(1l);
        assertThat(orderResponse.getCustomerEmail()).isEqualTo("pawar_kiran@live.in");
    }
}