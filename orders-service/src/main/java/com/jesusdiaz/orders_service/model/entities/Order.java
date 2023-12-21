package com.jesusdiaz.orders_service.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
        @Id
        @GeneratedValue(strategy = GenerationType. IDENTITY)
        private Long id;

        private String orderNumber;

        @OneToMany (mappedBy = "order", cascade= CascadeType.ALL)
        private List<OrderItems> orderItems;
    }
