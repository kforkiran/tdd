package com.kiran.tdd.controller;

import com.kiran.tdd.dto.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

     @GetMapping("/orders/{id}")
     public ResponseEntity<Order> getOrderDetails(@PathVariable("id") Long id){
          return ResponseEntity.ok().build();
     }
}
