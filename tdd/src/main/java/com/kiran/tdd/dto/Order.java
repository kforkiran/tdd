package com.kiran.tdd.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private String customerEmail;
    private String customerAddress;
}
