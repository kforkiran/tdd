package com.kiran.tdd.repository;

import com.kiran.tdd.entities.LineItem;
import com.kiran.tdd.entities.OrderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private OrderRepository orderRepository;
    private OrderEntity orderEntity;

    @BeforeEach
    void setUp() {
        final LineItem pens = LineItem.builder()
                .name("Pen")
                .quantity(9)
                .build();

        orderEntity = this.orderEntity.builder()
                .customerEmail("pawar_kiran@live.in")
                .customerAddress("Pune")
                .lineItems(List.of(pens))
                .build();
    }

    @Test
    void findById_shouldReturn_validOrder() {
        final OrderEntity savedEntity = testEntityManager.persistAndFlush(orderEntity);
        final Optional<OrderEntity> optionalOrder = orderRepository.findById(savedEntity.getId());
        final OrderEntity order = optionalOrder.get();
        assertAll(() -> assertThat(order).isNotNull(),
                () -> assertThat(order.getLineItems()).hasSize(1),
                () -> assertThat(order.getCustomerEmail()).isEqualTo("pawar_kiran@live.in"));
    }
}