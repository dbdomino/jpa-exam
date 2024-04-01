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
public class JpqlJoinTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("inner join 테스트")
    void jpqlJoinInner() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            String jpql = "select m from MemberJpql m join m.teamJpql t "; // 쿼리에 on 없으면, 자동으로 넣어준다. 연관관계 외래키 기준으로
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
                    .getResultList();
            for (MemberJpql member : resultList) {
//                String username = member.getUsername();
//                int age = member.getAge();
//                String teamId = member.getTeamJpql().getName(); // Member에서 지연로딩으로 연관관계 바꾸니 TeamId가 바뀔때 마다 select가 추가로 일어남.
//                System.out.println("username:" + username+", age:"+age + ", teamId:"+ teamId);
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
    @DisplayName("inner join On 조건 테스트")
    void jpqlJoinInner2() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            String jpql = "select m from MemberJpql m join m.teamJpql t on m.teamJpql.id=2"; // 쿼리에 on 없으면, 자동으로 넣어준다. 연관관계 외래키 기준으로
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
                    .getResultList();
            for (MemberJpql member : resultList) {
                System.out.println(member); // toString 정의함.
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
    @DisplayName("Outer join (Left) 테스트")
    void jpqlJoinOuter() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            String jpql = "select m from MemberJpql m left join m.teamJpql t  "; // 쿼리에 on 없으면, 자동으로 넣어준다. 연관관계 외래키 기준으로
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
    @DisplayName("Outer join (Left) Fetch 테스트")
    void jpqlJoinOuterFetch () {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            String jpql = "select m from MemberJpql m left join fetch m.teamJpql t  "; // 쿼리에 on 없으면, 자동으로 넣어준다. 연관관계 외래키 기준으로
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
    @DisplayName("Outer join (Left) On조건 테스트")
    void jpqlJoinOuter2() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            String jpql = "select m from MemberJpql m left outer join m.teamJpql t on t.name='팀1'   "; // 쿼리에 on 없으면, 자동으로 넣어준다. 연관관계 외래키 기준으로
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
//                    .setParameter("jogun1", "팀1")
                    .getResultList();
            System.out.println(resultList.size());
            for (MemberJpql member : resultList) {
//                System.out.println(member);
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
    @DisplayName("세타 조인 테스트")
    void jpqlCrossInner() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            String jpql = "select m from MemberJpql m, TeamJpql t where m.username=t.name"; // 쿼리에 on 없으면, 자동으로 넣어준다. 연관관계 외래키 기준으로
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
                    .getResultList();
            System.out.println("result size = "+resultList.size());
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
}
