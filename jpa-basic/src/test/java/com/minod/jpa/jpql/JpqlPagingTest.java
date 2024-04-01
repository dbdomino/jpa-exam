package com.minod.jpa.jpql;

import com.minod.jpa.domain.jpql.MemberJpql;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class JpqlPagingTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("기본 페이징API 사용 테스트")
    void jpqlPaging() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            String jpql = "select m from MemberJpql m order by m.username desc";
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
                    .setFirstResult(2) // 조회 시작위치는 어디부터?
                    .setMaxResults(3)  // 조회 최대 몇개까지?
                    .getResultList();
            for (MemberJpql member : resultList) {
                String username = member.getUsername();
                int age = member.getAge();
                System.out.println("username:" + username+", age:"+age);
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
