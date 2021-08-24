package com.kiran.tdd.controller;

import com.kiran.tdd.dto.Order;
import com.kiran.tdd.exceptions.OrderNotFoundException;
import com.kiran.tdd.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

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

}
