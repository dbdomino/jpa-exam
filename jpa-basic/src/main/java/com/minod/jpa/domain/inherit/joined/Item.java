package com.minod.jpa.domain.inherit.joined;

import jakarta.persistence.*;

//@Entity
//@Inheritance(strategy= InheritanceType.JOINED)
//@Inheritance(strategy= InheritanceType.SINGLE_TABLE) // 테이블 설계를 하나의 테이블로 때려넣은 경우 이렇게 쓰면됨 (Album Movie Book 테이블 없이 item테이블 하나만으로 쓰는 경우임), 상속받은 자식클래스로 persist하면, 부모 엔티티에 값이 포함되어 저장됨, 컬럼도 알맞게 alter table 지원됨.
// 이 전략 쓰면 item테이블, 서브테이블 조인해서 쓸 필요가 없어짐.
// 주의할 점, @DiscriminatorColum을 안 쓰더라도, DTYPE 컬럼이 자동으로 생성되고, 거기에 하위타입 Entity의 이름이 필수로 들어가게됨.(JPA 스팩에서 DTYPE을 쓸 수밖에 없음, 하이버네이트가 자동으로 지원하는 것이며, 테이블에서도 구분자가 필요하니 겸사겸사 이걸로 씀.)
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
// 주의할 점. TABLE_PER_CLASS 전략을 쓰면 @DiscriminatorColumn 어노테이션 붙여도 Item 테이블에 값이 들어가지 않고 자식 테이블에만 들어간다. DTYPE도 생성되지 않는다.(어노테이션 붙여도 사용안된단 소리)
@DiscriminatorColumn // DTYPE 이라는 컬럼을 추가로 만들고, subtype 테이블이름을 기본값으로 넣어준다.(구분목적으로 넣기위해 만듬) 자식 entity로 추가할 때 자동으로 DTYPE 에 넣어줌.
// 근데이거 이미 테이블 생긴 상태에서 추가하니 에러난다, 아래에 별도로 dtype필드를 추가하면 중복으로잡혀서 추가하면 안됨., DB에 컬럼을 별도로 추가하던지 해야함.
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
