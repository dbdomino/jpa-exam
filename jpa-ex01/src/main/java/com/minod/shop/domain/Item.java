package com.minod.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// 추상클래스로 만들어 사용
// 자식클래스들을 하나의 테이블로 다루기 위해 싱글 테이블 전략 사용
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype") // 구분컬럼 이름을 dtype으로 씀.
@Getter @Setter
public abstract class Item { // 추상클래스
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int StockQuantity; // 남은개수

    // 상품 하나에 카테고리를 여러개 가질 수 있다...
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
