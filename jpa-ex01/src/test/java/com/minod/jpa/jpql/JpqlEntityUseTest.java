package com.minod.jpa.jpql;

import com.minod.jpa.domain.jpql.MemberJpql;
import com.minod.jpa.domain.jpql.TeamJpql;
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
public class JpqlEntityUseTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("JPQL에 엔티티 직접 넣는 테스트")
    void jpqlEntityDirectUse1 () {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            String jpql1 = "select count(m.id) from MemberJpql m  "; // 쿼리에 on 없으면, 자동으로 넣어준다. 연관관계 외래키 기준으로
            String jpql2 = "select count(m) from MemberJpql m "; // 쿼리에 on 없으면, 자동으로 넣어준다. 연관관계 외래키 기준으로
//            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
            List<Object[]> resultList1 = em.createQuery(jpql1, Object[].class).getResultList();
            List<Object[]> resultList2 = em.createQuery(jpql2, Object[].class).getResultList();

            System.out.println(resultList1.get(0));
            System.out.println(resultList2.get(0));

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
    @DisplayName("JPQL에 엔티티를 파라미터로 넣는 테스트")
    void jpqlEntityParameterTest1 () {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            MemberJpql member1 = em.find(MemberJpql.class, 20);

//            String jpql = "select m from MemberJpql m where m = :member";
//            List resultList = em.createQuery(jpql)
//                    .setParameter("member", member1) // 엔티티를 넣더라도, 엔티티의 식별자를 가져와서 그걸로 조건구분한다.
            String jpql = "select m from MemberJpql m where m.id = :member";
            List resultList = em.createQuery(jpql)
                    .setParameter("member", member1.getId()) // 위에 주석친 것과 같은 SQL로 변환된다.
                    .getResultList();
            MemberJpql result = (MemberJpql) resultList.get(0);

            System.out.println(result.getUsername());

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
    @DisplayName("JPQL에 엔티티를 파라미터로 외래키비교 테스트")
    void jpqlEntityParameterTest2 () {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            TeamJpql team1 = em.find(TeamJpql.class, 1);

//            String jpql = "select m from MemberJpql m where m.team = :team";
//            List resultList = em.createQuery(jpql)
//                    .setParameter("team", team1) // 엔티티를 넣더라도, 엔티티의 식별자를 가져와서 그걸로 조건구분한다.
            String jpql = "select m from MemberJpql m where m.teamJpql.id = :team";
            List resultList = em.createQuery(jpql)
                    .setParameter("team", team1.getId()) // 위에 주석친 것과 같은 SQL로 변환된다.
                    .getResultList();
            MemberJpql result = (MemberJpql) resultList.get(0);

            System.out.println(result.getUsername());

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
