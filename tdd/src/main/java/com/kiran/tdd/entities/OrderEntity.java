package com.kiran.tdd.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customerEmail;
    private String customerAddress;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderEntity")
    private List<LineItem> lineItems;
}
