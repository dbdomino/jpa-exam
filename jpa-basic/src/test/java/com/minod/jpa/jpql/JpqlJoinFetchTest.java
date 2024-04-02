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
public class JpqlJoinFetchTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("Inner join fetch 테스트")
    void jpqlJoinInnerFetch () {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            String jpql = "select m from MemberJpql m join fetch m.teamJpql t "; // 쿼리에 on 없으면, 자동으로 넣어준다. 연관관계 외래키 기준으로
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
                    .getResultList();
            for (MemberJpql member : resultList) {
                System.out.println(member + ", TeamName:"+member.getTeamJpql().getName()) ;
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
    @DisplayName("Inner join 테스트")
    void jpqlJoinInnerNoFetch () {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            String jpql = "select m from MemberJpql m join m.teamJpql t "; // 쿼리에 on 없으면, 자동으로 넣어준다. 연관관계 외래키 기준으로
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
                    .getResultList();
            for (MemberJpql member : resultList) {
                System.out.println(member + ", TeamName:"+member.getTeamJpql().getName()) ;
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
                System.out.println(member + ", TeamName:"+member.getTeamJpql().getName()) ;
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
    void jpqlJoinOuterNoFetch () {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            String jpql = "select m from MemberJpql m left join m.teamJpql t  "; // 쿼리에 on 없으면, 자동으로 넣어준다. 연관관계 외래키 기준으로
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
                    .getResultList();
            for (MemberJpql member : resultList) {
                System.out.println(member + ", TeamName:"+member.getTeamJpql().getName()) ;
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
    @DisplayName("inner Collection join")
    void jpqlJoinCollectionFetch1 () {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            String jpql = "select t from TeamJpql t left join t.memberJpqlList ml  "; // 쿼리에 on 없으면, 자동으로 넣어준다. 연관관계 외래키 기준으로
            List<TeamJpql> resultList = em.createQuery(jpql, TeamJpql.class)
                    .getResultList();
            // 결과 조회시 OneToMany 연관관계로 컬렉션이 반환되어 2중루프로 값을 받아야 한다.
            for (TeamJpql team : resultList) {
                System.out.println("TeamId:"+team.getId() + ", TeamName:"+team.getName()) ;
                for (MemberJpql memberJpql:team.getMemberJpqlList()){
                    System.out.println("TeamId:"+team.getId() + " -> memberUserName="+memberJpql.getUsername()+", memberAge="+memberJpql.getAge());
                }
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
    @DisplayName("inner Collection join fetch")
    void jpqlJoinCollectionFetch2 () {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            String jpql = "select t from TeamJpql t left join fetch t.memberJpqlList ml  "; // 쿼리에 on 없으면, 자동으로 넣어준다. 연관관계 외래키 기준으로
            List<TeamJpql> resultList = em.createQuery(jpql, TeamJpql.class)
                    .getResultList();
            // 결과 조회시 OneToMany 연관관계로 컬렉션이 반환되어 2중루프로 값을 받아야 한다.
            for (TeamJpql team : resultList) {
                System.out.println("TeamId:"+team.getId() + ", TeamName:"+team.getName()+", 객체주소:"+team) ;
                for (MemberJpql memberJpql:team.getMemberJpqlList()){
                    System.out.println("TeamId:"+team.getId() + " -> memberUserName="+memberJpql.getUsername()+", memberAge="+memberJpql.getAge()+", 객체주소:"+memberJpql);
                }
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
