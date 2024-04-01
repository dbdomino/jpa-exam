package com.minod.jpa.jpql;

import com.minod.jpa.domain.jpql.MemberJpql;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// package com.minod.jpa.domain.jpql; 여기 도메인 정의된 것 기반으로 JPQL 을 통해서 예제 test만듬.

@Slf4j
@SpringBootTest
public class JpqlParamBindingTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("파라미터 바인딩 이름기준")
    void jpqlParamBindingName() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            //            Query query = em.createQuery("select m from MemberJpql m where m.username = 'user01'", MemberJpql.class);
            Query query = em.createQuery("select m from MemberJpql m where m.username = :username", MemberJpql.class);
            System.out.println("단계0");
            query.setParameter("username", "user02");
            System.out.println("단계1");
            List<MemberJpql> resultList = query.getResultList(); // 이게 있을 때 쿼리가 실행됨.
            System.out.println("단계2");

            for (MemberJpql result : resultList) {
                String username = result.getUsername();
                int age = result.getAge();
                System.out.println("username:" + username+", age:"+age);
            }
            System.out.println("단계3");

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
    @DisplayName("파라미터 바인딩 위치기준")
    void jpqlParamBindingLocation() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            Query query = em.createQuery("select m from MemberJpql m where m.username = ?1 or m.age =?2", MemberJpql.class);
            System.out.println("단계0");
            query.setParameter(1, "user02"); // 1부터 시작가능, 1 위치에 고정적으로 할당.
            query.setParameter(2, 33); // 파라미터가 2개 들어간다면 1씩 증가하며 잡아야 한다. 이후 파라미터/쿼리 변경시 살짝 불편할 수 있다.
            System.out.println("단계1");
            List<MemberJpql> resultList = query.getResultList(); // 이게 있을 때 쿼리가 실행됨.
            System.out.println("단계2");

            for (MemberJpql result : resultList) {
                String username = result.getUsername();
                int age = result.getAge();
                System.out.println("username:" + username+", age:"+age);
            }
            System.out.println("단계3");

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
