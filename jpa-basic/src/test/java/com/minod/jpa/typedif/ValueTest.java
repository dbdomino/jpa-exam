package com.minod.jpa.typedif;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ValueTest {
    @PersistenceUnit
    private EntityManagerFactory emf;


    @Test
    void find() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // JPA 통해서 객체를 받는 방식
//            Order order = em.find(Order.class, 1L);
//            Long memberId = order.getMemberId();

//            Member member = em.find(Member.class, memberId);
//            log.info("Order -> member result : {}", member.toString());

            // 일반적인 객체지향적으로 참조된 객체 받는 방식 (Order에 Member가 참조 되어있어야 함. 객체 설계들어갔다고 가정하면 아래처럼 쓰기가능)
//            Member findMember = order.getMember();


//            String a = "모몬1";
//            String b = a;
//
//            a = "모몬가1";

            Integer a = 10;
            Integer b = a;

            a = 20;

            System.out.println("a = "+a);
            System.out.println("b = "+b);


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
