package com.minod.jpa.ex;

import com.minod.jpa.domain.MemberSequenceGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class JPALifeCycle {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    void lifecycle(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작
        try {
            // 비영속 상태
            MemberSequenceGenerator member = new MemberSequenceGenerator();
            member.setId(300L);
            member.setUsername("Myname300");

            // 영속상태
            System.out.println("----- Before");
            em.persist(member);
            //  준영속 상태, 회원 엔티티를 영속성 컨텍스트에서 분리, commit 해도 적용안됨.
//            em.detach(member); // 단순히 이거만 쓰면 하이버네이트 버그 발생, ERROR org.hibernate.AssertionFailure --HHH000099: an assertion failure occurred (this may indicate a bug in Hibernate
            // insert에서는 detach 안쓰는게 낫다. 에러난다.
            System.out.println("----- After");


            //객체를 삭제,(삭제)
            em.remove(member);

            tx.commit();
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
        } finally {
            em.close();
        }
    }

//    @Test
    void main1(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작
        try {
            //

            tx.commit();
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
