package com.kiran.tdd.integrationtest;

import com.kiran.tdd.dto.Order;
import com.kiran.tdd.entities.OrderEntity;
import com.kiran.tdd.repository.OrderRepository;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OrderIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void createOrder_shouldCreateOrder_returnOrderDetails() throws JSONException {
        Order order = Order.builder()
                .customerAddress("Pune")
                .customerEmail("pawar_kiran@live.in")
                .build();
        ResponseEntity<String> orderResponseEntity = testRestTemplate.postForEntity("/orders", order, String.class);
        String expected = "{id: 1,customerEmail:pawar_kiran@live.in,customerAddress:Pune}";
        assertEquals(expected, orderResponseEntity.getBody(), false);
    }

    @Test
    void getOrder_shouldReturn_OrderDetails() throws JSONException {
        OrderEntity orderEntity = createOrder();
        ResponseEntity<String> orderResponse = testRestTemplate.getForEntity("/orders/" + orderEntity.getId(), String.class);
        String expected = "{id: " + orderEntity.getId() + ",customerEmail:pawar_kiran@live.in,customerAddress:Pune}";
        assertEquals(expected, orderResponse.getBody(), false);
    }

    private OrderEntity createOrder() {
        OrderEntity orderEntity = OrderEntity.builder()
                .customerEmail("pawar_kiran@live.in")
                .customerAddress("Pune")
                .build();
        return orderRepository.save(orderEntity);
    }
}
