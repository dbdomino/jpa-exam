package com.minod.jpa.jpql;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// package com.minod.jpa.domain.jpql; 여기 도메인 정의된 것 기반으로 JPQL 을 통해서 예제 test만듬.

@Slf4j
@SpringBootTest
public class JpqlMethodBasicTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("문자열 합치기")
    void jpqlConcat() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            String query="select 'a' || 'b' from MemberJpql m";
//            String query="select m.age || m.username from MemberJpql m";
//            String query="select substring(m.username, 2,4) from MemberJpql m";
//            String query="select trim(m.username) from MemberJpql m";
//            String query="select LENGTH(m.username) from MemberJpql m";
//            String query="select locate('de', 'abcdefg') from MemberJpql m";
//            String query="select ABS(-25) from MemberJpql m";
//            String query="select SQRT(25) from MemberJpql m"; // 음수는 제곱근으로 표현안됨, i로 표현불가, Double로 표현해야함.
//            String query="select MOD(25,3) from MemberJpql m";
//            String query="select size(m.memberJpqlList) from TeamJpql m"; // 컬렉션의 크기를 구하는 함수, JPQL 전용, Oneto
            String query="select size(m.memberJpqlList) from TeamJpql m"; // 컬렉션의 크기를 구하는 함수, JPQL 전용, Oneto
            TypedQuery<Integer> queryd = em.createQuery(query, Integer.class);
            List<Integer> resultList =  queryd.getResultList(); // arrayList로 반환

            for (Integer member : resultList) {
                System.out.println("member = " + member);
            }


            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
