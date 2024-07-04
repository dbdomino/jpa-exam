package com.minod.shop.domain;

import com.minod.shop.domain.status.DeliveryStatus;
import com.minod.shop.domain.valuetype.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch= FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // Enum 형식으로 상태값 줄 경우 유용
    private DeliveryStatus status; // [READY, PROCESSING, COMPLETE]
}
