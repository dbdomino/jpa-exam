package com.minod.shop.ex01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @PersistenceContext
    EntityManager em; // 스프링 부트가 EntityManager를 알아서 주입해준다. starter-data-jpa 의존성을 추가하면 된다.

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }
}
