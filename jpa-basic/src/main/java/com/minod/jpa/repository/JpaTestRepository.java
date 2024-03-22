package com.minod.jpa.repository;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class JpaTestRepository {
    private final EntityManager em;  // JPA 쓸려면 필수조건, EntityManager JPA의 모든 동작은 엔티티 매니저를 통해서 이루어진다.
    public JpaTestRepository(EntityManager em) {
        this.em = em;
    }

//    public Item save(Item item) {
//        em.persist(item); // jpa 저장
//        return item;
//    }
//
//    public void update(Long itemId, ItemUpdateDto updateParam) {
//        // 타입먼저 넣고, 다음으로 pk 넣으면 조회/값받기 가능
//        Item item = em.find(Item.class, itemId);
//        // 조회 후 set 메소드로 객체에 값 변경
//        // set메소드 호출하면 트랜잭션이 커밋되는 시점에 데이터들에 대해서 업데이트 쿼리를 만들어 DB에 날린다.
//        // update 메서드 끝날때 DB에 커밋이 나가며, 그러면서 JPA가 업데이트 쿼리를 만들어 쏴줌.
//        // 지금은 기본단계라 그런지 update 작업 하나에 대해서만 트랜잭션 보장하는 듯. (서비스 단위로 트랜잭션 보장은 서비스에서 시작되는 커밋 위치에 달린것이고, 이 update 자체만 가지고도 트랜잭션 지원 되어야 함.
//        // JPA가 어떻게 변경된 엔티티 객체를 찾는지 명확하게 이해하려면 영속성 컨텍스트라는 JPA 내부 원리를 이해해야 한다.
//        // 지금은 트랜잭션 커밋 시점에 JPA가 변경된 엔티티 객체를 찾아서 UPDATE SQL을 수행한다고 이해하면 된다.
//        // 테스트의 경우 마지막에 트랜잭션이 롤백되기 때문에 JPA는 UPDATE SQL을 실행하지 않는다. (핵심. update여러번 넣는작업을 구현하는건 어려움. 최종변경만 찍고 거기맞춰 update 날리네)
//        item.setItemName(updateParam.getItemName());
//        item.setPrice(updateParam.getPrice());
//        item.setQuantity(updateParam.getQuantity());
//    }
//
//    public Optional<Item> findById(Long id) { // Optional반환 왜하는데? 걍
//        // 타입먼저 넣고, 다음으로 pk 넣으면 조회/값받기 가능
//        Item item = em.find(Item.class, id);
////        em.remove(item);
//        return Optional.ofNullable(item);
//    }
//
//    public List<Item> findAll(ItemSearchCond cond) {
//        String jpql = "select i from Item i";// JPA 가 만들어주는 쿼리 jpql이라고 함.
//
//        Integer maxPrice = cond.getMaxPrice();
//        String itemName = cond.getItemName();
//
//        // 아래는 동적쿼리 생성과정
//        if (StringUtils.hasText(itemName) || maxPrice != null) {
//            jpql += " where";
//        }
//
//        boolean andFlag = false;
//        if (StringUtils.hasText(itemName)) {
//            jpql += " i.itemName like concat('%',:itemName,'%')";
//            andFlag = true;
//        }
//        if (maxPrice != null) {
//            if (andFlag) {
//                jpql += " and";
//            }
//            jpql += " i.price <= :maxPrice";
//        }
//        log.info("jpql={}", jpql);
//        TypedQuery<Item> query = em.createQuery(jpql, Item.class);
//        if (StringUtils.hasText(itemName)) {
//            query.setParameter("itemName", itemName);
//        }
//        if (maxPrice != null) {
//            query.setParameter("maxPrice", maxPrice);
//        }
//        return query.getResultList();
//    }
}
