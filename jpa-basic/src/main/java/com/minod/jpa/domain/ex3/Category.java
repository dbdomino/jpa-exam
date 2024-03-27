package com.minod.jpa.domain.ex3;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany  // 다대다 관계, 어노테이션으로 조인테이블 만들어서 N:M 관계를 표현, 실제로 이렇게 쓰지말고, Entity클래스 하나더 만들어서 1:N 관계로 하는게 더낫다고함
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns= @JoinColumn(name="CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name="ITEM_ID")
    )
    private List<Item> items = new ArrayList<>();



    // 카테고리 부모, 자식? 카테고리가 실제 보여질 때, 부모 카테고리, 자식 카테고리 표현하는 것인데, 이렇게 표현한다고 함.
    @ManyToOne
    @JoinColumn(name="PARENT_ID")
    private Category parent; // 자기자신?

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
