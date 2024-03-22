package com.minod.jpa;

import com.minod.jpa.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JpaMain2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.

        tx.begin(); // 트랜잭션 시작

        // JPA에서 모든 변경 작업은 트랜잭션 안에서 작업 해야 한다. 트랜잭션 단위 안에서가 아니라면 실행이 안된다.
        try {


            Member member1 = new Member();
            member1.setId(1L);
            member1.setName("HelloA");
            System.out.println(member1);

            em.persist(member1);

//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloADD");
//            log.info("hihi hindMember result : {}", findMember.toString());

            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
