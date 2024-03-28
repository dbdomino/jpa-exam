package com.minod.jpa.inherit;

import com.minod.jpa.domain.inherit.extend.MemberBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
public class MappedSuperclassTest {
    // entity 클래스들에 상속시켜서 필드만 추가하게 하는 어노테이션인 @MappedSuperclass 기능 테스트

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    void MappedSuperclass_test1(){
        // BaseEntity 에 @MappedSuperclass 어노테이션 설정
        // BaseEntity에 있는 필드를 추가하길 원하는 EntityClass에 BaseEntity를 extends 상속 시키면 된다고 함.
        // MemberBase에 상속시켜 테스트 진행
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            MemberBase member = new MemberBase();
            member.setId(1L);
            member.setName("이름1");
            member.setCreatedBy("생성자 이름1");
            member.setCreatedDate(LocalDateTime.now());

            em.persist(member); // movie 등록 하나로 item 테이블에 먼저 insert날리고, 그 뒤에 movie table에 insert 또 날린다.


            member.setLastModifiedBy("수정자1");
            member.setLastModifiedDate(LocalDateTime.now());

            MemberBase findMember = em.find(MemberBase.class, member.getId());
            System.out.println("findMember 조회한것 : "+findMember);

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
