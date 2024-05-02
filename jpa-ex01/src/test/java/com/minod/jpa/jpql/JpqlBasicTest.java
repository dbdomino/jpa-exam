package com.minod.jpa.jpql;

import com.minod.jpa.domain.jpql.MemberJpql;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// package com.minod.jpa.domain.jpql; 여기 도메인 정의된 것 기반으로 JPQL 을 통해서 예제 test만듬.

@Slf4j
@SpringBootTest
public class JpqlBasicTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("단순조회")
    void jpqlSelect() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            String query = "select m From MemberJpql m";
//            String name = "모몬";
//            if(name != null) {
//                String where = "where m.username like '%"+name+"%'";
//                query +=where;
//            }
//            System.out.println(query);

            TypedQuery<MemberJpql> query = em.createQuery("select m From MemberJpql m where m.username like '%ser%'",
                    MemberJpql.class);
            TypedQuery<MemberJpql> query2 = em.createQuery("select m.username, m.age From MemberJpql m where m.username like '%ser%'",
                    MemberJpql.class);

            List<MemberJpql> memberList = query.getResultList(); // arrayList로 반환

            // 보통 루프 돌려서 실행가능하다.
            for (MemberJpql memberJpql : memberList) {
                System.out.println("member = " + memberJpql);
            }


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
    @DisplayName("단순조회2")
    void jpqlSelectdif() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            String query = "select m From MemberJpql m";
//            String name = "모몬";
//            if(name != null) {
//                String where = "where m.username like '%"+name+"%'";
//                query +=where;
//            }
//            System.out.println(query);

//            TypedQuery<MemberJpql> query = em.createQuery("select m From MemberJpql m where m.username like '%ser%'",MemberJpql.class);
//            TypedQuery<MemberJpql> query = em.createQuery("select m.username, m.age From MemberJpql m where m.username like '%ser%'",MemberJpql.class);
            // 보통 루프 돌려서 실행가능하다.
//            for (MemberJpql memberJpql : memberList) {
//                System.out.println("username:" + memberJpql.getUsername()+", age:"+memberJpql.getAge());
//            }
            Query query = em.createQuery("select m.username, m.age From MemberJpql m where m.username like '%ser%'");

            List<Object[]> memberList = query.getResultList(); // arrayList로 반환하는데 제네릭을 Object [] 로 받자
            for (Object[] objects : memberList) {
                String username = (String) objects[0];
                Integer age = (Integer) objects[1];
                System.out.println("username:" + username+", age:"+age);
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
    @DisplayName("COUNT MAX SUM AVG 집계")
    void jpqlSelect1() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            MemberJpql member = new MemberJpql();
//            member.setUsername("user01");
//            em.persist(member);

/*
//            List<MemberJpql> result = em.createQuery("select m from MemberJpql m where username='user04'", MemberJpql.class).getResultList();
            for (MemberJpql memberResult : result) {
//                System.out.println("memberJpql username : "+memberResult.getUsername());
//                System.out.println("result toString : "+memberResult.toString());
            }*/
            Long result = (Long) em.createQuery("select count(m) from MemberJpql m where username='user04'")
                    .getSingleResult(); // 단일로 결과뱉을때 형변환 사용하기
            System.out.println("result = "+result);

            TypedQuery<Object[]> query = em.createQuery("SELECT COUNT(m), SUM(m.age), AVG(m.age) FROM MemberJpql m", Object[].class);
            List<Object[]> results = query.getResultList();

            for (Object[] resultArr : results) {
                Long count = (Long) resultArr[0];
                Long sum = (Long) resultArr[1];
                Double avg = (Double) resultArr[2];
                System.out.println("count : "+ count+", sum : "+sum+", avg : "+avg);
            }


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
    @DisplayName("Group by, Having, Order by")
    void jpqlSelect2() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            TypedQuery<Object[]> query = em.createQuery("SELECT m.age, COUNT(m), AVG(m.age) " +
                    "FROM MemberJpql m " +
                    "GROUP BY m.age " +
                    "HAVING AVG(m.age) >= 30 " +
                    "ORDER BY AVG(m.age) DESC", Object[].class);;
            List<Object[]> results = query.getResultList();

            for (Object[] resultArr : results) {
                Integer age = (Integer) resultArr[0];
                Long count = (Long) resultArr[1];
                Double avg = (Double) resultArr[2];
                System.out.println("age : "+ age+", count : "+count+", avg : "+avg);
            }


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
    @DisplayName("TypedQuery Query 반환결과")
    void jpqlSelect3() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            TypedQuery<Object[]> query= em.createQuery("SELECT m.age, COUNT(m), AVG(m.age) " +
//                    "FROM MemberJpql m " +
//                    "GROUP BY m.age " +
//                    "HAVING AVG(m.age) >= 30 " +
//                    "ORDER BY AVG(m.age) DESC", Object[].class);
            Query query = em.createQuery("SELECT m.age, COUNT(m), AVG(m.age) " +
                    "FROM MemberJpql m " +
                    "GROUP BY m.age " +
                    "HAVING AVG(m.age) >= 30 " +
                    "ORDER BY AVG(m.age) DESC");
            List<Object[]> results = query.getResultList();

            for (Object[] resultArr : results) {
                Integer age = (Integer) resultArr[0];
                Long count = (Long) resultArr[1];
                Double avg = (Double) resultArr[2];
                System.out.println("age : "+ age+", count : "+count+", avg : "+avg);
            }


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
