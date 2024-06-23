package com.minod.shop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class JpaMain {
    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(ZonedDateTime.now());
        System.out.println(LocalDateTime.now());
        /**
         * 2024-03-09
         * 15:24:35.462606200
         * 2024-03-09T15:25:37.689512500+09:00[Asia/Seoul]
         * 2024-03-09T15:24:35.462606200
         */
        /*
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("datasource");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작
//  JPA에서 모든 변경 작업은 트랜잭션 안에서 작업 해야 한다.

        try {
            Usera usera = new Usera();
            usera.setUsername("마리");

            System.out.println("-----------");
            em.persist(usera);
            System.out.println("member.id = "+usera.getId());
            System.out.println("-----------");
            System.out.println(LocalDate.now());
            System.out.println(LocalTime.now());
            System.out.println(LocalDateTime.now());

            tx.commit(); // 트랜잭션 종료, 이거선언되면 쿼리가 쫘좌작 나옴. 이게 지연 로딩
        } catch (Exception e) {
            tx.rollback();
        } finally {
        }*/

    }
}
