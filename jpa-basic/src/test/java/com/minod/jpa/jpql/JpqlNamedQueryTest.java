package com.minod.jpa.jpql;

import com.minod.jpa.domain.jpql.MemberNamedJpql;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// package com.minod.jpa.domain.jpql; 여기 도메인 정의된 것 기반으로 JPQL 을 통해서 예제 test만듬.

@Slf4j
@SpringBootTest
public class JpqlNamedQueryTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("Entity에 선언한 네임드쿼리로 사용")
    void jpqlNamedQueryTest1() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            MemberNamedJpql member1 = new MemberNamedJpql();
//            member1.setUsername("user01");
//            em.persist(member1);

//            em.createNamedQuery() 가 핵심
            TypedQuery<MemberNamedJpql> query = em.createNamedQuery("MemberNamedJpql.findByUsername2",MemberNamedJpql.class)
                    .setParameter("username", "user01");
            List<MemberNamedJpql> memberList = query.getResultList(); // arrayList로 반환

            // 보통 루프 돌려서 실행가능하다.
            for (MemberNamedJpql member : memberList) {
                System.out.println("member = " + member.getUsername());
            }


            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Test
    @DisplayName("Entity에 선언한 네임드쿼리 하드코딩해서 사용")
    void jpqlNamedQueryTest0() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 런타임 시점에서 문법 에러시 실행안됨.
            TypedQuery<MemberNamedJpql> query = em.createQuery("select m fromㅇ MemberNamedJpql m where m.username = :username",MemberNamedJpql.class)
                    .setParameter("username", "user01");
            List<MemberNamedJpql> memberList = query.getResultList(); // arrayList로 반환

            // 보통 루프 돌려서 실행가능하다.
            for (MemberNamedJpql member : memberList) {
                System.out.println("member = " + member.getUsername());
            }


            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
