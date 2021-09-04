package com.kiran.tdd.service;

import com.kiran.tdd.dto.Order;
import com.kiran.tdd.entities.LineItem;
import com.kiran.tdd.entities.OrderEntity;
import com.kiran.tdd.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;
    private OrderEntity orderEntity;

    @BeforeEach
    void setUp() {
        final LineItem pens = LineItem.builder()
                .name("Pen")
                .quantity(9)
                .build();

        orderEntity.builder()
                .customerEmail("pawar_kiran@live.in")
                .customerAddress("Pune")
                .lineItems(List.of(pens));
    }

    @Test
    void getOrderDetails_shouldReturn_OrderDetails() {
        given(orderRepository.findById(1l)).willReturn(Optional.of(orderEntity));
        Order orderResponse = orderService.getOrder(1l);
        assertThat(orderResponse.getCustomerEmail()).isEqualTo("pawar_kiran@live.in");
    }
}