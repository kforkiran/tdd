package com.kiran.tdd.controller;

import com.kiran.tdd.dto.Order;
import com.kiran.tdd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

     @Autowired
     private OrderService orderService;

     @GetMapping("/orders/{id}")
     public ResponseEntity<Order> getOrderDetails(@PathVariable("id") Long id){
          Order order = orderService.getOrder(id);
          return ResponseEntity.ok().body(order);
     }
}
