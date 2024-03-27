package com.minod.jpa.persistence;

import com.minod.jpa.domain.단방향1대N.MbrOne;
import com.minod.jpa.domain.단방향1대N.TeamOne;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class OnetoManyTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    void OnetoManyTest1(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            MbrOne member = new MbrOne();
            member.setUsername("member3");

            em.persist(member);

            TeamOne team = new TeamOne();
            team.setName("teamC");

//            team.getMembers().add(member); // OnetoMany단방향 관계이어서,
            // MbrOne 테이블의 member1 레코드에 TEAM_ID정보를 넣어줬다., OnetoMany 쓰면 이런게 가능하다.
            // 즉 MbrOne 테이블에서 외래키로 잡힌 필드의 값을 변경 가능해진다. team 객체를 통해서 말이다.
            // 이를 위해 hibernate에서 update 쿼리를 만들어 날린다고 하는데, 의도하지 않은 Update가 될 수도 있는거라 위험함.
            // 단순히 조회만 되게하려고, OnetoMany 단방향관계만으로 잘 안쓰고, 양방향 관계를 맺어 반대측개념으로 쓴다고 함.

            em.persist(team);
            team.setName("teamCCC");

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
