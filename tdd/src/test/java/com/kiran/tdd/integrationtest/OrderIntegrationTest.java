package com.kiran.tdd.integrationtest;

import com.kiran.tdd.dto.Order;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OrderIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void createOrder_shouldCreateOrder_returnOrderDetails() throws JSONException {
        Order order = Order.builder()
                .id(1l)
                .customerAddress("Pune")
                .customerEmail("pawar_kiran@live.in")
                .build();

        ResponseEntity<String> orderResponseEntity = testRestTemplate.postForEntity("/orders", order, String.class);
        String expected = "{id: 1,customerEmail:pawar_kiran@live.in,customerAddress:Pune}";
        JSONAssert.assertEquals(expected, orderResponseEntity.getBody(), false);
    }

}
