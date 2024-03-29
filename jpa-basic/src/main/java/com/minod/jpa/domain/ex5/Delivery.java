package com.minod.jpa.domain.ex5;

import com.minod.jpa.domain.ex5.extend.BaseEntity;
import jakarta.persistence.*;

//@Entity
public class Delivery  extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="DELIVERY_ID")
    private Long id;
    private String city;
    private String street;
    private String zipcode;

    private DeliveryStatus status; // enum

    @OneToOne(mappedBy = "delivery" ,fetch = FetchType.LAZY) // 1대1매핑
    private Order order;
}
