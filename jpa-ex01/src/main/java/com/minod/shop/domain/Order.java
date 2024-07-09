package com.minod.shop.domain;

import com.minod.shop.domain.status.DeliveryStatus;
import com.minod.shop.domain.status.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private List<OrderItem> orderItems = new ArrayList<>();

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
        orderItems.add(orderItem); // List<OrderItem>
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    // 비즈니스로직-주문생성, 연관된 엔티티들과 상호작용이 필요해서 여기에 정의, 생성자랑은 조금 다른 느낌
    public static Order create(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();

        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }
    // 비즈니스로직-주문취소, 주문상태를 취소로 바꾼다.
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMPLETE) {
            throw new IllegalStateException("이미 배송 완료된 주문.");
        }

        this.setStatus(OrderStatus.CANCEL);

        for (OrderItem orderItem : orderItems) {
            orderItem.cancel(); // 주문상품에 cancel() 추가
        }
    }

    // 비즈니스로직-주문 가격보기, 주문에 속한 상품들의 가격 합계를 보기
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice(); // 주문상품에 getTotalPrice() 추가
        }
        return totalPrice;
    }

}
