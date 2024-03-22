package com.minod.jpa;

import com.minod.jpa.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class JpaMain2 {
    public static void main(String[] args) {
        System.out.println("작동중1");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        System.out.println("작동중2");
        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작
        // JPA에서 모든 변경 작업은 트랜잭션 안에서 작업 해야 한다.
        System.out.println("작동중3");
        try {
            System.out.println("작동중40");
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
            System.out.println("작동중41");

            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            tx.rollback();
        } finally {
            em.close();
        }

        System.out.println("작동중5");

        emf.close();

    }
}
