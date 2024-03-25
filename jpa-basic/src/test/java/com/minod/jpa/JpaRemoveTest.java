package com.minod.jpa;

import com.minod.jpa.domain.Mbr3;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class JpaRemoveTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    void Remove1(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 이렇게만 해서는 수정이 안된다. 비영속 상태의 객체이기 때문이다.
            Mbr3 member1 = new Mbr3();
            member1.setId(400L);
            member1.setUsername("MynameDD");

            em.persist(member1);

            em.remove(member1);

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
    void Remove2(){

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            Mbr3 member1 = em.find(Mbr3.class, 500L);
            em.remove(member1);

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
    void Remove3(){

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 이건 안되는 소스임
            Mbr3 member1 = new Mbr3();
            member1.setId(400L);
            member1.setUsername("Myname401");
            em.remove(member1);

            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
