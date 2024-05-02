package com.minod.jpa.jpql;

import com.minod.jpa.domain.jpql.Day;
import com.minod.jpa.domain.jpql.DayLightJpql;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class JpqlTypeExpressTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("Enum 타입 표현 테스트1")
    void jpqlEnumTest1() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            DayJpql dayJpql = new DayJpql();
//            dayJpql.setAge(31);
//            dayJpql.setUsername("미노드3");
//            dayJpql.setDay(Day.MONDAY);
//            dayJpql.setBooltype(true);
//            em.persist(dayJpql);

            // Enum 타입을 JPQL에서 어떻게 표현하나?
            String jpql = "select m.username, 'Hello', m.day, m.booltype from DayJpql m" +
                    " where m.day = com.minod.jpa.domain.jpql.Day.SUNDAY"+ // Enum을 하드코딩해서 쓰려면, 이처럼 패키지명부터 enum명 까지 다 적어줘야 한다.
                    " and m.booltype = True"; // TRUE true True 대소문자 상관 없었다.
            List<Object[]> resultList = em.createQuery(jpql)
                    .getResultList();
            for (Object[] member : resultList) { // 기존에 Member 테이블과 관련없는 데이터를 조합해서 뽑으려면 이런식으로 하는것도 가능하다.
                System.out.println(member[0]);
                System.out.println(member[1]);
                System.out.println(member[2]);
                System.out.println(member[3]);
            }



            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    @Test
    @DisplayName("Enum 타입 표현 테스트2")
    void jpqlEnumTest2() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            DayJpql dayJpql = new DayJpql();
//            dayJpql.setAge(31);
//            dayJpql.setUsername("미노드3");
//            dayJpql.setDay(Day.MONDAY);
//            dayJpql.setBooltype(true);
//            em.persist(dayJpql);

            // Enum 타입을 JPQL에서 어떻게 표현하나?
            String jpql = "select m.username, 'Hello', m.day, m.booltype from DayJpql m" +
                    " where m.day = :daytype1"+ // Enum을 하드코딩해서 쓰려면, 이처럼 패키지명부터 enum명 까지 다 적어줘야 한다.
                    " and m.booltype = True"; // TRUE true True 대소문자 상관 없었다.
            List<Object[]> resultList = em.createQuery(jpql)
                    .setParameter("daytype1", Day.SUNDAY) // 패키지명까지 다 하드코딩하기 보다, 파라미터 매핑하면 더 간결하게 쓰기가능
                    .getResultList();
            for (Object[] member : resultList) { // 기존에 Member 테이블과 관련없는 데이터를 조합해서 뽑으려면 이런식으로 하는것도 가능하다.
                System.out.println(member[0]);
                System.out.println(member[1]);
                System.out.println(member[2]);
                System.out.println(member[3]);
            }



            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    @Test
    @DisplayName("상속관계에서 DTYPE 조건으로 찾기")
    void jpqlEnumTest3() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            DayLightJpql dayJpql = new DayLightJpql();
            dayJpql.setAge(33);
            dayJpql.setUsername("미노드4");
            dayJpql.setDay(Day.WEDNESDAY);
            dayJpql.setBooltype(true);
            dayJpql.setLight("lilight");
            em.persist(dayJpql);

            // Enum 타입을 JPQL에서 어떻게 표현하나?
            String jpql = "select m.username, 'Hello', m.day, m.booltype from DayJpql m" +
                    " where m.day = :daytype1"+ // Enum을 하드코딩해서 쓰려면, 이처럼 패키지명부터 enum명 까지 다 적어줘야 한다.
                    " and m.booltype = True"+ //TRUE true True 대소문자 상관 없었다.
                    " and type(m) = DayLightJpql "; // type(m) = 자식클래스 -> 자식클래스에 선언된 Discriminator value가 맞다면 조건일치
            List<Object[]> resultList = em.createQuery(jpql)
                    .setParameter("daytype1", Day.WEDNESDAY) // 패키지명까지 다 하드코딩하기 보다, 파라미터 매핑하면 더 간결하게 쓰기가능
                    .getResultList();
            for (Object[] member : resultList) { // 기존에 Member 테이블과 관련없는 데이터를 조합해서 뽑으려면 이런식으로 하는것도 가능하다.
                System.out.println(member[0]);
                System.out.println(member[1]);
                System.out.println(member[2]);
                System.out.println(member[3]);
            }



            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

}
