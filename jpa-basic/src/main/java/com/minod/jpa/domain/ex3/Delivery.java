package com.minod.jpa.domain.ex3;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

//@Entity
public class Delivery {
    @Id @GeneratedValue
    @Column(name="DELIVERY_ID")
    private Long id;
    private String city;
    private String street;
    private String zipcode;

    private DeliveryStatus status; // enum

    @OneToOne(mappedBy = "delivery") // 1대1매핑
    private Order order;
}
