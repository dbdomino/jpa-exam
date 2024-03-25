package com.minod.jpa;

import com.minod.jpa.domain.Mbr3;
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
public class JpaUpdateTest {
    @Autowired
    private JpaSaveRepository repository;
    @PersistenceUnit
    private EntityManagerFactory emf;

// 단순히 이거만 쓰면 하이버네이트 버그 발생, ERROR org.hibernate.AssertionFailure --HHH000099: an assertion failure occurred (this may indicate a bug in Hibernate
    @Test
    void Update1(){

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 이렇게만 해서는 수정이 안된다. 비영속 상태의 객체이기 때문이다.
            Mbr3 member1 = new Mbr3();
            member1.setId(400L);
            member1.setUsername("MynameDD");

            // persist 후 setter로 값 변경하면 update쿼리날림 알아서
            em.persist(member1);
//            em.detach(member1); // insert 에서 준영속으로 변환은 에러나는데... 방법이 있긴하려나
            member1.setUsername("Myname300");

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
    void Update2(){

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 이렇게만 해서는 수정이 안된다. 비영속 상태의 객체이기 때문이다.
            Mbr3 member1 = em.find(Mbr3.class, 300L);
            member1.setUsername("Myname302"); // 수정하기
//            em.detach(member1); // update취소를 위한 detach 는 문제없이 가능

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
