package com.minod.jpa;

import com.minod.jpa.domain.Mbr1;
import com.minod.jpa.domain.Mbr2;
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
public class JpaSaveRepositoryTest {
    @Autowired
    private JpaSaveRepository repository;
    @PersistenceUnit
    private EntityManagerFactory emf;

//    @Test
    void repoSave1(){
        repository.save("helloD");
    }

    @Test
    void SaveDbSequence(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 매 값마다 저장할 때 db에서 시퀀스 받아와야 함.
            Mbr1 member1 = new Mbr1();
            member1.setUsername("A");
            Mbr1 member2 = new Mbr1();
            member2.setUsername("B");
            Mbr1 member3 = new Mbr1();
            member3.setUsername("C");
            log.info("-----------------------------");
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            System.out.println("member1 = "+member1.getId());
            System.out.println("member2 = "+member2.getId());
            System.out.println("member3 = "+member3.getId());

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
    void SaveMemorySequence(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 시퀀스 증가량 30인 것이지만, 트랜잭션 내에서 시퀀스 한번만 받아오고, 다음 시퀀스보다 값이 작을경우
            // db에서 다음 시퀀스 값을 조회한 뒤, 다음시퀀스 전 까지 1씩 증가 가능하다면 메모리에서 1씩 증가하며 id에 매김,
            // 그래서 기본적으로 시퀀스 증가량 기본값이 50인 것이다. (기본키는 의미없는 단순히 구분을 위한 값으로, 중간에 공백이 생기더라도 상관없다고 보기 때문이기도 하다.
            // insert할 때마다 sequence 조회하는 것도 자원이니 이거 줄이는게 목적
            //
            // 시퀀스에서 select nextval('public.mbr2_seq') 호출 한다면 호출 단위로 값이 할당된다.
            // 동시성 문제를 피하기 위해 여러 id를 할당하는 방법으로도 사용할 수 있다.
            Mbr2 member1 = new Mbr2();
            member1.setUsername("A");
            Mbr2 member2 = new Mbr2();
            member2.setUsername("B");
            Mbr2 member3 = new Mbr2();
            member3.setUsername("C");
            log.info("-----------------------------");
            em.persist(member1); // 시퀀스 db에서 받아옴
            em.persist(member2); // 시퀀스 메모리에서 받아옴
            em.persist(member3); // 시퀀스 메모리에서 받아옴.

            System.out.println("member1 = "+member1.getId());
            System.out.println("member2 = "+member2.getId());
            System.out.println("member3 = "+member3.getId());

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
