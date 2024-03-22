package com.minod.maintest;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class JpaMainTest {
//    private final EntityManager em;  // JPA 쓸려면 필수조건, EntityManager JPA의 모든 동작은 엔티티 매니저를 통해서 이루어진다.
//    public JpaMainTest(EntityManager em) {
//        this.em = em;
//    }


    @Test
    void mainssn() {


    }

//
//
//    @Test
//    void main() {
////        EntityManagerFactory emf = Persistence.createEntityManagerFactory("datasource");
////        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
//        tx.begin(); // 트랜잭션 시작
////  JPA에서 모든 변경 작업은 트랜잭션 안에서 작업 해야 한다.
//
//        try {
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");
//
//            tx.commit(); // 트랜잭션 종료
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//
//        emf.close();
//
//    }
}
