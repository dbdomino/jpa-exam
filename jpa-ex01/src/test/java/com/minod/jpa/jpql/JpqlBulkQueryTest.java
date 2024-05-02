package com.minod.jpa.jpql;

import com.minod.jpa.domain.jpql.MemberJpql;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// package com.minod.jpa.domain.jpql; 여기 도메인 정의된 것 기반으로 JPQL 을 통해서 예제 test만듬.

@Slf4j
@SpringBootTest
public class JpqlBulkQueryTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

//    @Test
    @DisplayName("단순 일괄 update 예제")
    void jpqlUpdate01() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            MemberJpql memberJpql = em.find(MemberJpql.class, 20);
            System.out.println("memberJpql 20 -> "+memberJpql.getUsername()+", "+memberJpql.getCreatedBy());

            // 모든 컬럼에 업데이트하는 쿼리,
            String queryStr="update MemberJpql m set m.createdBy='momon2' ";
            // update는 createQuery를 쓰며, QueryType으로 만들고, getResultList()가 아니라, executeUpdate()를 쓴다.
            Query query = em.createQuery(queryStr);
            int result = query.executeUpdate(); // executeUpdate() 는 int로 반환.
//            query.executeUpdate(); // 이렇게 반환안받고 수행도 가능.

            System.out.println("update result: "+result); // update 한번으로 6개 변경되었다고 나옴.

            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
        } finally {
            em.close();
        }
    }

//    @Test
    @DisplayName("update 시 영속성컨텍스트에 update 내역 적용안됨. ")
    void jpqlUpdate02() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            MemberJpql memberJpql = em.find(MemberJpql.class, 20);
            System.out.println("memberJpql 20 -> "+memberJpql.getUsername()+", "+memberJpql.getCreatedBy());

            // 모든 컬럼에 업데이트하는 쿼리,
            String queryStr="update MemberJpql m set m.createdBy='momon4' ";
            // update는 createQuery를 쓰며, QueryType으로 만들고, getResultList()가 아니라, executeUpdate()를 쓴다.
            Query query = em.createQuery(queryStr);
            int result = query.executeUpdate(); // executeUpdate() 는 int로 반환.
//            query.executeUpdate(); // 이렇게 반환안받고 수행도 가능.

            System.out.println("update result: "+result); // update 한번으로 6개 변경되었다고 나옴.
            System.out.println("memberJpql 20 -> "+memberJpql.getUsername()+", "+memberJpql.getCreatedBy());

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
    @DisplayName("update 시 영속성컨텍스트에 update 내역 적용되도록 clear적용 ")
    void jpqlUpdate021() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            MemberJpql memberJpql = em.find(MemberJpql.class, 20);
            System.out.println("memberJpql 20 -> "+memberJpql.getUsername()+", "+memberJpql.getCreatedBy());

            // 모든 컬럼에 업데이트하는 쿼리,
            String queryStr="update MemberJpql m set m.createdBy='momon4' ";
            Query query = em.createQuery(queryStr);
            int result = query.executeUpdate();

            em.clear(); // 핵심.

            System.out.println("update result: "+result);
            memberJpql = em.find(MemberJpql.class, 20); // find 다시 불러와야됨. 영속성컨텍스트 초기화되었으므로.
            System.out.println("memberJpql 20 -> "+memberJpql.getUsername()+", "+memberJpql.getCreatedBy());

            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
        } finally {
            em.close();
        }
    }


//    @Test
    @DisplayName("update 시 영속성컨텍스트에 JPQL 말고 객체로 update 내역 적용되는지확인. ")
    void jpqlUpdate03() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            MemberJpql memberJpql = em.find(MemberJpql.class, 20);
            System.out.println("memberJpql 20 -> "+memberJpql.getUsername()+", "+memberJpql.getCreatedBy());

            // 모든 컬럼에 업데이트하는 쿼리,
//            String queryStr="update MemberJpql m set m.createdBy='momon4' ";
//            // update는 createQuery를 쓰며, QueryType으로 만들고, getResultList()가 아니라, executeUpdate()를 쓴다.
//            Query query = em.createQuery(queryStr);
//            int result = query.executeUpdate(); // executeUpdate() 는 int로 반환.
////            query.executeUpdate(); // 이렇게 반환안받고 수행도 가능.
            memberJpql.setCreatedBy("momonini");

//            System.out.println("update result: "+result); // update 한번으로 6개 변경되었다고 나옴.
            System.out.println("memberJpql 20 -> "+memberJpql.getUsername()+", "+memberJpql.getCreatedBy());

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
