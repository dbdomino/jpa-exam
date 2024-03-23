package com.minod.jpa;

import com.minod.jpa.domain.Mbr1;
import com.minod.jpa.repository.JpaSaveRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class JpaUpdateRepositoryTest {
    @Autowired
    private JpaSaveRepository repository;
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    void Update1(){

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            Mbr1 member1 = new Mbr1();
            member1.setUsername("A");


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
