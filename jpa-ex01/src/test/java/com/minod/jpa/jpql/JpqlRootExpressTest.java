package com.minod.jpa.jpql;

import com.minod.jpa.domain.jpql.MemberJpql;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// package com.minod.jpa.domain.jpql; 여기 도메인 정의된 것 기반으로 JPQL 을 통해서 예제 test만듬.

@Slf4j
@SpringBootTest
public class JpqlRootExpressTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("경로 표현식, 상대필드 ")
    void jpqlExpressTest1() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            //  상태 필드. 객체의 단순 필드를 의미하며, 탐색의 끝임.
            // m.username m.id 같은 구체적인 값 필드를 의미한다.
            String jpql = "select m.username, 'Hello', TRUE from MemberJpql m";
            List<Object[]> resultList = em.createQuery(jpql)
                    .getResultList();
            for (Object[] member : resultList) {
                System.out.println(member[0]);
                System.out.println(member[1]);
                System.out.println(member[2]);
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
    @DisplayName("경로 표현식, 단일 값 연관 필드")
    void jpqlExpressTest2() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 단일 값 연관 필드
            // m.teamJpql 은 @ManyToOne 연관관계로 단일 엔티티 타입으로 지정되어있음.
            // m.teamJpql을 select하기 위해 묵시적인 내부 조인(inner join) 이 발생 한다고 함. (이게 진짜 해깔림) 쿼리 날려보면 join되서 쿼리 만들어진게 확인됨.
            // jpql에 조인을 안적었지만 쿼리 변환 과정에서, m.teamJpql이라는 객체 형식으로 반환하기 위해 알아서 join이 일어난 것(이게 묵시적 내부조인 이다.)
            // m.teamJpql.name 처럼 추가로 탐색이 가능함.
            String jpql = "select m.teamJpql, 'Hello', TRUE from MemberJpql m";
            List<Object[]> resultList = em.createQuery(jpql)
                    .getResultList();
            for (Object[] member : resultList) {
                System.out.println(member[0]);
                System.out.println(member[1]);
                System.out.println(member[2]);
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
    @DisplayName("경로 표현식, 컬렉션 값 연관 필드")
    void jpqlExpressTest3() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 컬렉션 값 연관 필드
            // 컬렉션으로 받아지기는 한다만, 받은 컬렉션으로 어떤 값을 꺼낼 것인지 별도로 로직이 들어가야 한다.
            // select에서 collection 형식으로 꺼낼 수 있다만, 이후에 쓰려면 손이 많이가는게 단점.
            // 또한 여기도 묵시적 내부 조인이 발생하게 된다.(OneToMany 라서 여러 select 날리는 것보단 낫긴한데...)
            String jpql = "select m.memberJpqlList from TeamJpql m";
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
                    .getResultList();
            for (MemberJpql member : resultList) {
                System.out.println(member.getClass()+" , "+member.getAge()+", "+member.getUsername());
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
    @DisplayName("경로 표현식, 컬렉션 값 연관 필드, 명시적 조인")
    void jpqlExpressTest4() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 컬렉션 값 연관 필드
            // 또한 여기도 묵시적 내부 조인이 발생하게 된다.(OneToMany 라서 여러 select 날리는 것보단 낫긴한데...)
            // 차라리 명시적으로 Join을 적어주는 명시적 Join을 쓰자.
            String jpql = "select t.memberJpqlList from TeamJpql t join t.memberJpqlList m";
            List<MemberJpql> resultList = em.createQuery(jpql, MemberJpql.class)
                    .getResultList();
            for (MemberJpql member : resultList) {
                System.out.println(member.getClass()+" , "+member.getAge()+", "+member.getUsername());
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
    @DisplayName("경로 표현식, 단일 값 연관 필드, 명시적 조인")
    void jpqlExpressTest5() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 단일 값 연관 필드
            // m.teamJpql 은 @ManyToOne 연관관계로 단일 엔티티 타입으로 지정되어있음.
            // m.teamJpql을 select하기 위해 묵시적인 내부 조인(inner join) 이 발생 한다고 함. (이게 진짜 해깔림) 쿼리 날려보면 join되서 쿼리 만들어진게 확인됨.
            // JPQL에 join을 명시해줘서 join 발생하는 것을 알 수 있도록 함.
            // m.teamJpql.name 처럼 추가로 탐색이 가능함.
            String jpql = "select m.teamJpql, 'Hello', TRUE from MemberJpql m join m.teamJpql t";
            List<Object[]> resultList = em.createQuery(jpql)
                    .getResultList();
            for (Object[] member : resultList) {
                System.out.println(member[0]);
                System.out.println(member[1]);
                System.out.println(member[2]);
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
