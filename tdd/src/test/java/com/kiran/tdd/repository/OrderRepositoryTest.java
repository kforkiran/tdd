package com.kiran.tdd.repository;

import com.kiran.tdd.entities.OrderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findById_shouldReturn_validOrder() {
        final OrderEntity orderEntity = new OrderEntity(1l, "pawar_kiran@live.in", "Pune");
        testEntityManager.persistAndFlush(orderEntity);
        final Optional<OrderEntity> optionalOrder = orderRepository.findById(1l);
        assertTrue(optionalOrder.isPresent());
    }
}