package com.minod.jpa.jpql;

import com.minod.jpa.domain.jpql.AddressJ;
import com.minod.jpa.domain.jpql.MemberDTO;
import com.minod.jpa.domain.jpql.MemberJpql;
import com.minod.jpa.domain.jpql.TeamJpql;
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
public class JpqlProjectionTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("select 프로젝션 엔티티 1")
    void jpqlProjection1() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            TypedQuery<MemberJpql> query = em.createQuery("select m from MemberJpql m where m.username = :username", MemberJpql.class);
//            query.setParameter("username", "user02");
//            List<MemberJpql> resultList = query.getResultList(); // 이게 있을 때 쿼리가 실행됨.

            List<MemberJpql> resultList = em.createQuery("select m from MemberJpql m where m.username = :username", MemberJpql.class)
                    .setParameter("username", "user02")
                    .getResultList();
            // 이 경우 resultList안의 member객체들은 모두 영속성 컨택스트에 관리 대상이 된다.
            int changeAge = 20;

            for (MemberJpql result : resultList) {
                changeAge=changeAge+5;
//                result.setAge(changeAge);  // setter 로 객체의 변경 진행

                String username = result.getUsername();
                int age = result.getAge();
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
    @DisplayName("select 프로젝션 연관 관계 엔티티 가져오기")
    void jpqlProjection2() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // createQuery 2번째 인자로 어떤 클래스로 받을지 정하는게 핵심.
            // select 프로젝션과 맞춰야 결과를 편하게 받아올 수 있다.
            List<TeamJpql> resultList = em.createQuery("select m.teamJpql from MemberJpql m where m.username = :username", TeamJpql.class)
                    .setParameter("username", "user02")
                    .getResultList();
            // teamJpql을 불러오고싶다. 이렇게 하면 쿼리가 Member만 뒤지는게 아니라 team도 조회해야되므로 실제 sql에선 join이 발생한다.

            for (TeamJpql result : resultList) {
                System.out.println("teamId:" + result.getId()+", teamName:"+result.getName());
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
    @DisplayName("select 프로젝션 값 타입 가져오기")
    void jpqlProjection3() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            OrderJpql order = new OrderJpql();
//            order.setOrderAmount(29);
//            order.setAddress(new AddressJ("city01", "street01", "zip01"));
//            em.persist(order);
//            em.flush();
//            em.clear();
            // createQuery 2번째 인자로 어떤 클래스로 받을지 정하는게 핵심.
            // select 프로젝션과 맞춰야 결과를 편하게 받아올 수 있다. AddressJ
            List<AddressJ> resultList = em.createQuery("select m.address from OrderJpql m where m.id = :id", AddressJ.class)
                    .setParameter("id", 52)
                    .getResultList();
            // teamJpql을 불러오고싶다. 이렇게 하면 쿼리가 Member만 뒤지는게 아니라 team도 조회해야되므로 실제 sql에선 join이 발생한다.

            for (AddressJ result : resultList) {
                System.out.println("getCity:" + result.getCity()+", getStreet:"+result.getStreet()+", getZipcode:"+result.getZipcode());
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
    @DisplayName("select 프로젝션 스칼라 타입 가져오기")
    void jpqlProjection4() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            OrderJpql order = new OrderJpql();
//            order.setOrderAmount(29);
//            order.setAddress(new AddressJ("city01", "street01", "zip01"));
//            em.persist(order);
//            em.flush();
//            em.clear();
            // createQuery 2번째 인자로 어떤 클래스로 받을지 정하는게 핵심.
            List<MemberJpql> resultList = em.createQuery("select m.username, m.age from MemberJpql m where m.id = :id", MemberJpql.class)
                    .setParameter("id", 20)
                    .getResultList();
            System.out.println(resultList.get(0).getClass());
            System.out.println(resultList.get(0).getUsername());
            System.out.println(resultList.get(0).getAge());

            System.out.println(resultList.get(0).getId());
            System.out.println(resultList.get(0).getCreatedBy());






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
    @DisplayName("select 프로젝션 스칼라 타입 가져오기2")
    void jpqlProjection54() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 스칼라 타입으로 특정 값만 select 하려면, 타입매핑이 어렵다.
            // 아래처럼 타입 지정 안할 경우 값들이 object[] 배열로 반환된다. JPA 규칙이다.
            // 결국 값타입 엔티티타입에 별개 값들만 받기 위해서, 프로젝션을 별개 값으로 설정한다면, 반환은 Object[]로 반환되며, 여러개 일 경우 List에 Object[]로 담아 반환한다.
            // Query 로 하는 방법
            List resultList = em.createQuery("select m.username, m.age from MemberJpql m where m.id = :id")
                    .setParameter("id", 20)
                    .getResultList();
//             // 배열에 들은 순서는 select 프로젝션에서 선언한 순서에 기반한다.
            for (Object o : resultList) {
                Object[] m = (Object[]) o;
                System.out.println(m[0]); // user03
                System.out.println(m[1]); // 37
            }

            List resultListSingle = em.createQuery("select m.username from MemberJpql m where m.id = :id")
                    .setParameter("id", 20)
                    .getResultList();
//             // 배열에 들은 순서는 select 프로젝션에서 선언한 순서에 기반한다.
            for (Object o : resultListSingle) {
                System.out.println(o); // user03
            }

            // TypedQuery로 하는 방법
            List<Object[]> resultList2 = em.createQuery("select m.username, m.age from MemberJpql m where m.id = :id",Object[].class)
                    .setParameter("id", 20)
                    .getResultList();
            System.out.println(resultList2.get(0).getClass());
            // 배열에 들은 순서는 select 프로젝션에서 선언한 순서에 기반한다.
            for (Object[] o : resultList2) {
                System.out.println(o[0]); // user03
                System.out.println(o[1]); // 37
            }

            // 별도 DTO를 만들어 조회하는 방법, 젤 귀찮고 젤 깔끔하다.
            // select 프로젝션에 new로 DTO를 입력해줘야 한다. (패키지 명까지 전부다.)(생성자 호출 필요, DTO에 생성자 존재해야함.)
            List<MemberDTO> resultList3 = em.createQuery("select new com.minod.jpa.domain.jpql.MemberDTO( m.username, m.age) from MemberJpql m where m.id = :id", MemberDTO.class)
                    .setParameter("id", 20)
                    .getResultList();
            System.out.println(resultList3.get(0).getClass());
            // 배열에 들은 순서는 select 프로젝션에서 선언한 순서에 기반한다.
            for (MemberDTO o : resultList3) {
                System.out.println(o.getUsername()); // user03
                System.out.println(o.getAge()); // 37
            }


            // select 프로젝션에 new로 MemberJpql를 사용할 수도 있다. (패키지 명까지 전부다.)(생성자 별도 만들어야 하나, DTO를 사용하는 것을 권장 한다고 함.)
            List<MemberJpql> resultList4 = em.createQuery("select new com.minod.jpa.domain.jpql.MemberJpql(m.username, m.age) from MemberJpql m where m.id = :id", MemberJpql.class)
                    .setParameter("id", 20)
                    .getResultList();
            System.out.println(resultList4.get(0).getClass());
            // 배열에 들은 순서는 select 프로젝션에서 선언한 순서에 기반한다.
            for (MemberJpql o : resultList4) {
                System.out.println(o.getUsername()); // user03
                System.out.println(o.getAge()); // 37
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
    @DisplayName("select 프로젝션 스칼라 타입 가져오기3")
    void jpqlProjection6() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
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
