package com.kiran.tdd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiran.tdd.dto.Order;
import com.kiran.tdd.exceptions.OrderNotFoundException;
import com.kiran.tdd.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void getOrder_ShouldReturnOrderDetails() throws Exception {
        given(orderService.getOrder(anyLong())).willReturn(new Order(99l, "pawar_kiran@live.in", "Pune"));
        mockMvc.perform(get("/orders/99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("customerEmail").value("pawar_kiran@live.in"));
    }

    @Test
    void getCar_notFoundException() throws Exception {
        given(orderService.getOrder(anyLong())).willThrow(new OrderNotFoundException());
        mockMvc.perform(get("/orders/99")).andExpect(status().isNotFound());
    }

    @Test
    void createOrder() throws Exception {
        final Order order = Order.builder()
                .id(1l)
                .customerAddress("pawar_kiran@live.in")
                .customerAddress("Pune")
                .build();

        given(orderService.createOrder(order)).willReturn(order);

        mockMvc.perform(post("/orders")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1l));
    }
}
