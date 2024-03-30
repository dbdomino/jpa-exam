package com.minod.jpa.domain.ex6;

import com.minod.jpa.domain.ex5.Order;
import com.minod.jpa.domain.ex5.extend.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//@Entity
public class Member extends BaseEntity {
    @Id
    @Column(name= "MEMBER_ID")
    private Long id;

//    private String name;
//    private String city;
//    private String street;
    @Embedded
    private Address address; // 값타입으로 참조해서 속성넣기

    private String zipcode;

    // 멤버 하나에 주문이 여러개 있을 수 있다  (Member) 1 to (Order) N
    @OneToMany(mappedBy = "member") // 양방향매핑 주인설정
    private List<Order> orders = new ArrayList<>(); // 주인이 아닌 양방향 참조로 쓰이는 단방향 참조라서, 관례상 new ArrayList<>() 로 초기화미리함.

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
