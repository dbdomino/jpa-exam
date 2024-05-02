package com.minod.jpa.jpql;

import com.minod.jpa.domain.jpql.MemberDTO;
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
public class JpqlSubqueryTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("subquery 샘플1 ")
    void jpqlSubQueryTest1() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 나이가 평균보다 많은 회원
            String jpql = "select m from MemberJpql m where m.age > (select avg(m2.age) from MemberJpql m2) ";
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
                    .getResultList();
            for (MemberJpql member : resultList) {
                System.out.println(member);
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

    @Test
    @DisplayName("subquery 샘플2 ")
    void jpqlSubQueryTest2() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 주문건이 한건이상인 고객
            String jpql = "select m from MemberJpql m where (select count(o) from OrderJpql o where m = o.memberJpql ) > 0 ";
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
                    .getResultList();
            for (MemberJpql member : resultList) {
                System.out.println(member);
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

    @Test
    @DisplayName("subquery Exist 샘플 ")
    void jpqlSubQueryTest3() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 주문건이 한건이상인 고객
            String jpql = "select m from MemberJpql m where exists (select t from m.teamJpql t where t.name = '팀A')";
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
                    .getResultList();
            for (MemberJpql member : resultList) {
                System.out.println(member);
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

    @Test
    @DisplayName("subquery ALL 샘플 ")
    void jpqlSubQueryTest4() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // Product 의 재고수량 보다 Order의 주문량이 많은 주문들
            String jpql = "select o from OrderJpql o where o.orderAmount > ALL (select p.stockAmount from ProductJpql p)";
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
                    .getResultList();
            for (MemberJpql member : resultList) {
                System.out.println(member);
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

    @Test
    @DisplayName("subquery ANY 샘플 ")
    void jpqlSubQueryTest5() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 어떤 팀이든 팀에 소속된 회원
            String jpql = "select m from MemberJpql m where m.teamJpql = ANY (select t from TeamJpql t)";
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
                    .getResultList();
            for (MemberJpql member : resultList) {
                System.out.println(member);
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

    @Test
    @DisplayName("subquery From절 버스쿼리 가능한지 샘플 ")
    void jpqlSubQueryTest6() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // from 절에 서브쿼리 컴파일 에러는 안나지만, 아직 에러가 있는 것 같다.
            // (aliases are required in CTEs and in subqueries occurring in from clause) 에러남
            String jpql = "select m.username, m.age from (select a.username, a.age from MemberJpql as a) as m ";
            List<MemberDTO> resultList = em.createQuery(jpql, MemberDTO.class)
                    .getResultList();
            for (MemberDTO member : resultList) {
                System.out.println(member);
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
