package com.minod.shop.domain;

import com.minod.shop.domain.Item.Item;
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

    private int orderPrice; // 상품정보에 가격은 있는데, 주문가격을 별도로 적는이유는? 주문에서 할인같은게 적용된 가격 보이기 위해
    private int count;

    // 생성 메서드, 생성자와는 다르게 주문상품 엔티티를 생성함. (초기화 개념의 상위 같음. 생성자 대신 이름있는 메서드로 엔티티를 생성하며, 다른 엔티티와 호환 수행)
    // 주문은 추가안함. 주문상품만 만듬
    public static OrderItem create(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count); // item엔티티에도 개수 차감, 갱신

        return orderItem;
    }


    // 비즈니스로직-주문 취소, 주문취소시 Item엔티티에 개수 되돌림, 증가
    public void cancel() {
        getItem().addStock(count);
    }
    // 비즈니스로직-주문 전체가격 조회, 주문에 들어있는 아이템의 가격을 계산 (Item의 가격에 추가로직이 수행된 가격orderPrice와 개수를 곱한 것을 반환)
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
