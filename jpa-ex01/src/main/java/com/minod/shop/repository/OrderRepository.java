package com.minod.shop.repository;

import com.minod.shop.domain.Member;
import com.minod.shop.domain.Order;
import com.minod.shop.domain.OrderSearch;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/** void save(Order order)
 *  Order findOne(Long id)
 */
@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    // 주문내역 조회라면, 전체 조회도 있을 수 있을거고... orderSearch는 조건
    public List<Order> findMany(OrderSearch orderSearch){
        String jpql = "select o From Order o join o.member m";
        boolean isFirstCondition = true; // where 붙일지 판단

        // 조건-주문 상태 추가
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and ";
            }
            jpql += " o.status = :status";
        }
        // 조건-회원 이름 추가
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and ";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<Order> query = em.createQuery(jpql, Order.class).setMaxResults(1000); // 최대 1000건
        // 조건에 맞춰 파라미터 네임기준 매핑
        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())){
            query = query.setParameter("name", orderSearch.getMemberName());
        }

        // 쿼리 결과 List로 받기
        return query.getResultList();


        // 내가만든 간략한 정적쿼리
//        return em.createQuery("select i from Order i where i.member.name = :name", Order.class)
//                .setParameter("name",orderSearch.getMemberName())
//                .getResultList();


    }

    // JPQL 말고 JPA Criteria로 처리하기, 쿼리가 컴파일 타임에 체크되므로 런타임 오류를 줄일 수 있다.
    // Criteria는 JPA 표준스펙이지만 너무 복잡하다... 이걸 대신해서 쓰는게 QueryDsl
    public List<Order> findManyCriteria(OrderSearch orderSearch){
        /** JPA Criteria API의 주요 구성 요소
         CriteriaBuilder: 쿼리를 생성하기 위한 팩토리 클래스입니다. EntityManager를 통해 얻을 수 있습니다.
         CriteriaQuery: 쿼리의 루트 객체를 정의하고, 결과 타입을 설정합니다.
         Root: 쿼리의 시작점을 나타내며, 엔티티를 지정합니다.
         Predicate: 쿼리의 조건을 정의합니다. 조건은 CriteriaBuilder를 사용하여 생성합니다.
         */
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Order, Member> m = o.join("member", JoinType.INNER); //Order와 Member 조인

        List<Predicate> criteria = new ArrayList<>(); // 쿼리의 조건

        // 조건-주문 상태 추가
        if(orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }
        // 조건-회원 이름 추가
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name = cb.like(m.<String>get("name"), "%"+orderSearch.getMemberName()+"%");
            criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()]))); //?? 뭐이리 기냐
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);

        return query.getResultList();
    }

}
