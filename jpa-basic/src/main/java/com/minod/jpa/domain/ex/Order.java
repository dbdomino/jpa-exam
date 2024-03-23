package com.minod.jpa.domain.ex;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue
    @Column(name="ORDER_ID") // 컬럼명은 DBA가 짜주는 것이다. 주는데로 해야한다.
    private Long id;
    @Column(name="MEMBER_ID")
    private Long memberId;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING) // DB엔 Enum타입이 없으므로 어노테이션 붙여서 구분해줘야 함. EnumType.ORDINAL 하면 순서가 꼬일 수 있음.
    private OrderStatus status; // enum

    /**
     *         System.out.println(LocalDate.now());
     *         System.out.println(LocalTime.now());
     *         System.out.println(ZonedDateTime.now());
     *         System.out.println(LocalDateTime.now());
     * 2024-03-09
     * 15:24:35.462606200
     * 2024-03-09T15:25:37.689512500+09:00[Asia/Seoul]
     * 2024-03-09T15:24:35.462606200
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
