package com.minod.jpa.domain.ex6;

import com.minod.jpa.domain.ex5.extend.BaseEntity;
import com.minod.jpa.domain.ex5.items.Item;
import jakarta.persistence.*;

//@Entity
public class OrderItem  extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

//    @Column(name = "ORDER_ID")
//    private Long orderId;
    // Order 하나에 OrdeItem은 여러개일 수 있다. (OrderItem)N to (Order)1 이니 ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORDER_ID") // 변수가 객체가 되버려서, 실제 테이블의 컬럼명을 매핑하기 위해
    private Order order;  // Order에 양방향 매핑으로 선언해둠.

//    @Column(name = "ITEM_ID")
//    private Long itemId;
    // 참조를 여러 객체를 하네...
    // OrderItem에는 Item정보가 들어가 있을 것이다. (OrderItem)N to (Item) 1 이니 ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ITEM_ID")
    private Item item;

    private int orderPrice;
    private int count;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
