package com.minod.jpa.jpql;

import com.minod.jpa.domain.jpql.MemberJpql;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class JpqlIntroduceTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    void jpqlSelect() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            MemberJpql member = new MemberJpql();
            member.setUsername("user04");
//            member.setCreatedBy("user04");
//            member.setCreatedDate(LocalDateTime.now());
//            member.setLastModifiedBy("user04");
//            member.setLastModifiedDate(LocalDateTime.now());

            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m From MemberJpql m";
            String name = "모몬";
            if(name != null) {
                String where = "where m.username like '%"+name+"%'";
                query +=where;
            }
            System.out.println(query);

            // jpql 로 select 날려보기, 결과로 받을 타입과 컬렉션 memberList 준비해야 함.
            List<MemberJpql> memberList = em.createQuery("select m From MemberJpql m where m.username like '%ser%'" ,
                    MemberJpql.class)
            // 위에가 끝이 아니라, 아래가 끝이다. 어떤 타입으로 select결과 반환할 지 결정하는 문구 추가
                    .getResultList(); // arrayList로 반환
//            System.out.println(memberList.getClass());

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
    void jpqlQueryDynamical() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            MemberJpql member = new MemberJpql();
//            member.setUsername("user04");
//            member.setCreatedBy("user04");
//            member.setCreatedDate(LocalDateTime.now());
//            member.setLastModifiedBy("user04");
//            member.setLastModifiedDate(LocalDateTime.now());

//            em.persist(member);


            String query1 = "select m From MemberJpql m";
            String name = "user01";
            if(name != null) {
                String where = " where m.username like '%"+name+"%'";
                query1 +=where;
            }
            System.out.println(query1);

            // jpql 로 select 날려보기, 결과로 받을 타입과 컬렉션 memberList 준비해야 함.
            List<MemberJpql> memberList = em.createQuery(query1,
                            MemberJpql.class)
                    // 위에가 끝이 아니라, 아래가 끝이다. 어떤 타입으로 select결과 반환할 지 결정하는 문구 추가
                    .getResultList(); // arrayList로 반환
//            System.out.println(memberList.getClass());

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
    void jpqlQueryCriteria() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            String query1 = "select m From MemberJpql m";
            String name = "모몬";
//            if(name != null) {
//                String where = "where m.username like '%"+name+"%'";
//                query1 +=where;
//            }

            // Criteria로 JPA를 위한 동적 쿼리 만들기
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<MemberJpql> query = cb.createQuery(MemberJpql.class);  // cb.createQuery 이게 중요한거다. 이걸로 자동완성 해서 쓰네
            //CriteriaBuilder가 여러 동적 쿼리를 만드는 연산자, 메서드를 조합하도록 해준다.
            Root<MemberJpql> m = query.from(MemberJpql.class); // from절 만드는 옵션
            // 위에 CriteriaBuilder가 제공하는 cb.createQuery랑 중복 같은데
            // 쿼리 복잡해지면 아래가 진짜 엄청 복잡해질 것 같다. 또한 dba가 쿼리를 다 짜주는거면? 이걸로 구현하는게 더 번거로울 것 같다.
            CriteriaQuery<MemberJpql> cq = query.select(m).where(cb.equal(m.get("username"), "user01"));// select(from절 m)    where(조건)

            // 조건에 맞춰 메서드 체이닝도 가능하다. 근데 어렵다. 이 사용방법 모르면 못쓴다. 어떻게든 때려 맞추는 수 밖에.
            CriteriaQuery<MemberJpql> cq2 = query.select(m);
            if (name != null) {
                cq2= cq2.where(cb.equal(m.get("username"), "user01"));
            }

            // jpql 로 select 날려보기, 쿼리는 Criteria로 만듬. 난 왜 Criteria가 더 불편하게 느껴질까? Criteria 생성하는게 보기가 실다.
            // 그러나 Criteria로 만들면, 컴파일에러로 오타나 실수 잡는게 쉬워 진다고 한다.
            System.out.println(cq2); // org.hibernate.query.sqm.tree.select.SqmSelectStatement@bf2f2ce
            List<MemberJpql> memberList = em.createQuery(cq)
                    .getResultList(); // arrayList로 반환

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
    void jpqlQueryNative() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            List<MemberJpql> memberList = em.createNativeQuery("select id, username, create_member, created_date from member_jpql", MemberJpql.class)
                    .getResultList(); // arrayList로 반환하는데 select에 위 처럼 컬럼 필요한거만 선언하면, MemberJpql에 빈값은 매핑이 안되어 Unable to find column position by name: last_modified_date 이런 에러가 난다.
            // result를 받기 위한 dto 형식으로 만들던지 해야하나? 아니면 항상 전체로 받도록 해야하나?

            // 보통 루프 돌려서 실행가능하다.
            for (MemberJpql memberJpql : memberList) {
//                System.out.println("member = " + memberJpql);
                System.out.println("member = " + memberJpql.getUsername());
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
