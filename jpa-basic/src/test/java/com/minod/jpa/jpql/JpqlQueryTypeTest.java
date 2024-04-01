package com.minod.jpa.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// package com.minod.jpa.domain.jpql; 여기 도메인 정의된 것 기반으로 JPQL 을 통해서 예제 test만듬.

@Slf4j
@SpringBootTest
public class JpqlQueryTypeTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("select 프로젝션 스칼라 타입 가져오기4")
    void jpqlProjection6() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // TypedQuery 타입이 아닌 Query 타입으로 하면 이런것도 가능하다. 반환타입에 의존적이지 않고 원하는 값 출력 가능.
            // 기존에 Member 테이블과 관련없는 데이터를 조합해서 뽑으려면 이런식으로 하는것도 가능하다.
            String jpql = "select m.username, 'Hello', TRUE from MemberJpql m";
            List<Object[]> resultList = em.createQuery(jpql)
                    .getResultList();
            for (Object[] member : resultList) {
                System.out.println(member[0]);
                System.out.println(member[1]);
                System.out.println(member[2]);
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
