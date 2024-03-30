package com.minod.jpa.embededtype;

import com.minod.jpa.domain.embededtype.AddressC;
import com.minod.jpa.domain.embededtype.MemberEmbeded;
import com.minod.jpa.domain.embededtype.MemberReuseEmbeded;
import com.minod.jpa.domain.embededtype.PeriodC;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class EmbededTypeMappingTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

//    @Test
    void embededTest() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            MemberEmbeded member = em.find(MemberEmbeded.class, memberId);
            MemberEmbeded member = new MemberEmbeded();
            member.setUsername("member01");
            member.setAddress(new AddressC("city1", "street1", "22033"));
            member.setPeriod(new PeriodC());

            em.persist(member); // 값을 각각 프리미티브타입으로 설정한게 아닌 객체로 참조시켜서 매핑도 가능하다는 점 알아두자.

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
    @DisplayName("임베디드 타입 값 재사용하기")
    void embededTest2() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            MemberEmbeded member = em.find(MemberEmbeded.class, memberId);
            MemberReuseEmbeded member = new MemberReuseEmbeded();
            member.setUsername("member02");
            member.setAddressA(new AddressC("city33", "street33", "22033"));
            member.setAddressB(new AddressC("city55", "street55", "22055"));
            member.setPeriod(new PeriodC());

            em.persist(member); // 값을 각각 프리미티브타입으로 설정한게 아닌 객체로 참조시켜서 매핑도 가능하다는 점 알아두자.

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
