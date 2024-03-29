package com.minod.jpa.proxyonjpa;

import com.minod.jpa.domain.양방향관계.MbrEach;
import com.minod.jpa.repository.JpaTestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ProxyLifeCycleTest {
    @Autowired
    private JpaTestRepository repository;
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태일 때, 프록시 객체를 초기화하면? ")
    void Find01() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            MbrEach findMember = em.getReference(MbrEach.class, 102);
            System.out.println("find Member type : "+ findMember.getClass());

            em.detach(findMember); // 준영속 상태로 전환 했음.
            System.out.println("findMember getUsername : "+findMember.getUsername()); // 여기서 에러남. could not initialize proxy
            // 영속성 컨텍스트에서 findMember 관리 한하다보니, 프록시 객체 초기화가 불가능해서 에러남.  - no Session


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
