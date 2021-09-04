package com.kiran.tdd.controller;

import com.kiran.tdd.dto.Order;
import com.kiran.tdd.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class OrderController {

     @Autowired
     private OrderService orderService;

     @GetMapping("/orders/{id}")
     public ResponseEntity<Order> getOrderDetails(@PathVariable("id") Long id){
          Order order = orderService.getOrder(id);
          return ResponseEntity.status(OK).body(order);
     }

     @PostMapping("/orders")
     public ResponseEntity<Order> createOrder(@RequestBody Order order){
          final Order response = orderService.createOrder(order);
          return ResponseEntity.status(CREATED).body(order);
     }
}
