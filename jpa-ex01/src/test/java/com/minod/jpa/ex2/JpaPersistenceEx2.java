package com.minod.jpa.ex2;


import com.minod.jpa.domain.ex2.Order;
import com.minod.jpa.domain.ex2.OrderItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


/** ex2 경로의 소스로 테스트 작성
 *
 */

@Slf4j
@SpringBootTest
public class JpaPersistenceEx2 {
    @PersistenceUnit
    private EntityManagerFactory emf;


    @Test
    void test1(){

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            Order order = new Order();
            order.addOrderItem(new OrderItem());

            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }

    }

}
