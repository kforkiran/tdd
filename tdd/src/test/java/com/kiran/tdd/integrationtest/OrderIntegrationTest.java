package com.kiran.tdd.integrationtest;

import com.kiran.tdd.dto.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OrderIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getOrder_returnsOrderDetails() {
        ResponseEntity<Order> orderResponseEntity = testRestTemplate.getForEntity("/orders/99", Order.class);
        assertThat(orderResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(orderResponseEntity.getBody().getCustomerEmail()).isEqualTo("pawar_kiran@live.in");
        assertThat(orderResponseEntity.getBody().getCustomerAddress()).isEqualTo("Pune");
        assertThat(orderResponseEntity.getBody().getId()).isEqualTo(9);
    }
}
