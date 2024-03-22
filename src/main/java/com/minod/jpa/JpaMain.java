package com.minod.jpa;

import com.minod.jpa.domain.Usera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

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

        EntityTransaction tx = em.getTransaction();
        tx.begin();

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

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
        }*/

    }
}
