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

@Slf4j
@SpringBootTest
public class JpqlCaseIfTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("Case식 으로 조건 표현")
    void jpqlCaseTest1() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // Member 의 Age로 case 식을 이용한 표현, 나중에 쿼리 DSL로 이런 jpql을 만들 수 있다.
            String jpql = "select case when m.age <= 10 then '유아' when m.age >=20 then '성인' else '청소년' end" +
                    ", m.age, m.username from MemberJpql m";
            List<Object[]> resultList = em.createQuery(jpql)
                    .getResultList();
            for (Object[] member : resultList) { // 기존에 Member 테이블과 관련없는 데이터를 조합해서 뽑으려면 이런식으로 하는것도 가능하다.
                System.out.println(member[0] + " " + member[1] + " " + member[2]);
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
    @DisplayName("조건문 coalesce 예제")
    void jpqlCoalesceTest() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // coalesce 예제 (값, null이면 반환할 값)
            String jpql = "select coalesce(m.username, '이름 없는 회원'), m.username, m.age from MemberJpql m";
            List<Object[]> resultList = em.createQuery(jpql)
                    .getResultList();
            for (Object[] member : resultList) { // 기존에 Member 테이블과 관련없는 데이터를 조합해서 뽑으려면 이런식으로 하는것도 가능하다.
                System.out.println(member[0] + " " + member[1] + " " + member[2]);
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
    @DisplayName("조건문 nullif 예제")
    void jpqlNullifTest() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // nullif 로 두값이 같으면 null, 다르면 첫번째 값 반환.
            String jpql = "select nullif(m.username, '유후인')" +
                    ", m.age, m.username from MemberJpql m";
            List<Object[]> resultList = em.createQuery(jpql)
                    .getResultList();
            for (Object[] member : resultList) { // 기존에 Member 테이블과 관련없는 데이터를 조합해서 뽑으려면 이런식으로 하는것도 가능하다.
                System.out.println(member[0] + " " + member[1] + " " + member[2]);
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
