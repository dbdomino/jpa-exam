package com.minod.jpa.domain.ex6;

import com.minod.jpa.domain.ex5.Delivery;
import com.minod.jpa.domain.ex5.Member;
import com.minod.jpa.domain.ex5.extend.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Table(name = "ORDERS")
public class Order  extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="ORDER_ID") // 컬럼명은 DBA가 짜주는 것이다. 주는데로 해야한다.
    private Long id;

//    @Column(name="MEMBER_ID") // 참조기반으로 바꾸니 필요없어짐.
//    private Long memberId;
    // Order가 Member를 바라보는 기준은? 하나의 회원이 여러개의 주문을 날린다. 회원1 주문 N 인데,
    // 주문 입장에선 주문당 회원은 반드시 하나다. 회원이 하나가 되어야하니 (Order)ManyToOne(member)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private com.minod.jpa.domain.ex5.Member member; // Member 에 양방향 매핑으로 선언해둠.

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // 양방향매핑 주인설정
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

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

//    public Long getMemberId() {
//        return memberId;
//    }
//
//    public void setMemberId(Long memberId) {
//        this.memberId = memberId;
//    }
    public com.minod.jpa.domain.ex5.Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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

    public void addOrderItem(OrderItem orderItem) { // 연관관계 편의 메소드 로 만듬
        orderItems.add(orderItem);
        orderItem.setOrder(this); // 호출한 객체에 나의 객체 Order를 넣어줌. 양방향 연관관계로 양쪽에 객체 정보갱신목적
    }
}
