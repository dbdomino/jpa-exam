package com.minod.shop.domain.Item;

import com.minod.shop.domain.Category;
import com.minod.shop.exception.NotEnoughStockException;
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
    private int StockQuantity; // 남은개수, 싱글턴객체에 재고를보관? 스레드 충돌일어나지...

    // 상품 하나에 카테고리를 여러개 가질 수 있다...
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // 엔티티에 비즈니스 로직 추가
    // 재고가 증가하는 로직, 데이터를 가진 쪽에서 비즈니스 로직을 구현하는게 가장 관리하기 좋아 여기에 추가함.
    // 이게 객체지향적 이라고 한다.
    public void addStock(int quantity) {
        this.StockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.StockQuantity - quantity;
        if (restStock < 0) {
//            throw new RuntimeException("재고가 부족합니다.");
            throw new NotEnoughStockException("재고가 부족합니다."); // 커스텀 에러
        }
        this.StockQuantity = restStock; // 재고감소 후 적용
    }
}
