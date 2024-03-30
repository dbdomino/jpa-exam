package com.minod.jpa.domain.datacollectiontype;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 아래 MemberCollection 클래스 하나로 테이블이 3개가 만들어짐
 * 01:15:05.329 [main] DEBUG org.hibernate.SQL --
 *     create table public.address_history (
 *         member_id bigint not null,
 *         city varchar(255),
 *         street varchar(255),
 *         zipcode varchar(255)
 *     )
 * 01:15:05.335 [main] DEBUG org.hibernate.SQL --
 *     create table public.favorite_food (
 *         member_id bigint not null,
 *         food_name varchar(255)
 *     )
 * 01:15:05.336 [main] DEBUG org.hibernate.SQL --
 *     create table public.member_collection (   주 테이블이 되겠지  member_id  username 뿐이다.
 *         member_id bigint not null,
 *         username varchar(255),
 *         primary key (member_id)
 *     )
 */
@Entity
public class MemberCollection {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;

    // 값 타입 컬렉션 추가, 매핑은 어떻게 하나? (기존에 Entity객체를 만들어 매핑하는 것과는 미묘하게 다르다. 차이점, 여기선 FAVORITE_FOOD나 ADDRESS_HISTORY에 대한 Entity 객체를 만들지 않고 컬랙션으로 불러 쓰는것임. 또한 @OneToMany 연관관계와 비슷함.)
    @ElementCollection
    @CollectionTable(name="FAVORITE_FOOD", joinColumns = @JoinColumn(name="MEMBER_ID")) // joinColumns 이거 왜쓰나? DB에선 Favorit_Food라는 별도 테이블로 데이터를 다루기 때문이다. 연관관계가 있기에 써줘야 함.
    @Column(name="FOOD_NAME") //  FAVORITE_FOOD테이블에서 가져올 값이 하나일 경우 어떤 컬럼으로 매핑되는지 여기서 선언 가능함.
    private Set<String> favoriteFoods = new HashSet<>();
    @ElementCollection
    @CollectionTable(name="ADDRESS_HISTORY", joinColumns = @JoinColumn(name="MEMBER_ID")) // ADDRESS_HISTORY 테이블에서 연관관계로 join할 컬럼이 무엇인지 표시해줘야 함.
    private List<AddressHistory> addressHistories = new ArrayList<>();//

    // 값타입으로 MEMBER_COLLECTION 테이블에 필드추가하려면 아래처럼 씀, 지금은 별도 테이블로 분리 된 상태로 Entity가 아닌 값 타입 컬렉션으로 매핑하는 것이다.
//    @Embedded
//    private FavoritFood favoritFood;
//    @Embedded
//    private AddressHistory addressHistory;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressHistory> getAddressHistories() {
        return addressHistories;
    }

    public void setAddressHistories(List<AddressHistory> addressHistories) {
        this.addressHistories = addressHistories;
    }
}
