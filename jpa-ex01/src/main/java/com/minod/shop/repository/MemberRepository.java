package com.minod.shop.repository;

import com.minod.shop.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 기능 설명
 `save()`
 `findOne()`
 `findByName()`
 `findAll()`
 */

@Repository  // 스프링 빈으로 등록, JPA 예외를 스프링 기반 예외로 예외 변환해줌.
public class MemberRepository {
//    @PersistenceUnit  // 엔티티 메니저 팩토리(EntityManagerFactory) 주입
//    private EntityManagerFactory emf;

    @PersistenceContext  // 엔티티 메니저(EntityManager) 주입
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); // JPQL
    }

}
