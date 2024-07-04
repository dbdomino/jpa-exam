package com.minod.shop.domain;

import com.minod.shop.domain.status.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 주인
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    // cascade로 여기서 주문 등록시 orderItem에도 주문아이템에 등록되도록 하는것
    private List<OrderItem> orderItems;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 주인
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // Date말고 LocalDateTime 사용
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

    // 연관관계 메서드 Member member,List<OrderItem> orderItems, Delivery delivery 연관관계 다루는 인스턴스에도 여기변경내역을 적용시켜주기 위해 만듬.
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
