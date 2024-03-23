package com.minod.jpa;

import com.minod.jpa.domain.Mbr1;
import com.minod.jpa.repository.JpaTestRepository;
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
public class JpaTestRepositoryTest {
    @Autowired
    private JpaTestRepository repository;
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    void find1() {repository.find1();}
    @Test
    void find2() {repository.find2();}

    @Test
    void update1() {
        EntityManager emc = emf.createEntityManager();

        EntityTransaction tx = emc.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        // JPA에서 모든 변경 작업은 트랜잭션 안에서 작업 해야 한다. 트랜잭션 단위 안에서가 아니라면 실행이 안된다.
        try {
            Mbr1 findMember = emc.find(Mbr1.class, 1L);
            findMember.setUsername("HelloADD");
            log.info("hihi hindMember result : {}", findMember.toString());

            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
        } finally {
            emc.close();
        }
    }
}
