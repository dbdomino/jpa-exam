package com.minod.shop.domain;

import com.minod.shop.domain.Item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    // @JoinTable 쓰면서 구분하기 위해, 여기 ID는 테이블명_id 형식으로 컬럼명을 지정한다.
    @Column(name ="category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
           joinColumns = @JoinColumn(name = "category_id"),
           inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //==연관관계 메서드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
