package com.minod.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // 주인
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(fetch = FetchType.LAZY)  // 주인
    @JoinColumn(name = "order_id")  // 주인 쪽 엔티티는 외래 키를 관리하고, @JoinColumn 어노테이션을 사용
    private Order order;

    private int orderPrice;
    private int count;


}
