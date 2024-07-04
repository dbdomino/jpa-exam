package com.minod.shop.domain;

import com.minod.shop.domain.valuetype.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")   // 외래키가 있는 곳이 연관관계의 주인
    private List<Order> orders = new ArrayList<>();

}
