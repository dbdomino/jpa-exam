package com.minod.jpa.proxyonjpa;

import com.minod.jpa.domain.양방향관계.MbrEach;
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
public class ProxyUtilTest {
    @Autowired
    private JpaTestRepository repository;
    @PersistenceUnit
    private EntityManagerFactory emf;


    @Test
    void ProxyUtilTest() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            MbrEach findMember1 = em.getReference(MbrEach.class, 102);
//            MbrEach findMember2 = em.find(MbrEach.class, 102); // 이거 열면 findMember1에 참조된 프록시객체가 초기화 된다. (db에 접근해서 값 가져옴)

            // 프록시 클래스명 확인
            System.out.println("---------object");
            System.out.println("findMember = "+findMember1.getClass());
            System.out.println("findMember = "+findMember1.getClass().getName());

            // 프록시 객체 수동으로 초기화하기 (메소드 호출하는방법 말고도 가능함)
//            Hibernate.initialize(findMember1);

            // 프록시 인스턴스 초기화 여부확인
            System.out.println("1. PersistenceUnitUtil().isLoaded(Object entity) : "+emf.getPersistenceUnitUtil().isLoaded(findMember1));
//            System.out.println("findMember initialize, getUsername: "+findMember1.getUsername());
//            System.out.println("1-2. PersistenceUnitUtil().isLoaded(Object entity) : "+emf.getPersistenceUnitUtil().isLoaded(findMember1));


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
