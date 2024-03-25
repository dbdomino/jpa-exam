package com.minod.jpa.ex;

import com.minod.jpa.domain.MbrNoPersist;
import com.minod.jpa.domain.MbrPersist;
import com.minod.jpa.domain.Team;
import com.minod.jpa.domain.ex.Order;
import com.minod.jpa.domain.양방향관계.MbrEach;
import com.minod.jpa.domain.양방향관계.TeamEach;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// 객체의 연관관계 유무로 예제 만들기
// 객체의 참조를 테스트 하기위함.
@Slf4j
@SpringBootTest
public class JpaPersistence {
    @PersistenceUnit
    private EntityManagerFactory emf;


//    @Test
    void nonPersist() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {// 연관관계 없는 조회
            Order order = em.find(Order.class, 1L);
            Long memberId = order.getMemberId();

            MbrNoPersist member = em.find(MbrNoPersist.class, memberId);

            Team team = em.find(Team.class, member.getId());
            log.info("MemberId에 해당하는 team name? : {}", team.getName());

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
    void yesPersist() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            /*Team team = em.find(Team.class, "402");

            MbrPersist member = new MbrPersist();
            member.setUsername("monami3");

            member.setTeam(team); // 변수대신 객체로 set
            em.persist(member);*/

            MbrPersist member = em.find(MbrPersist.class, "52"); // 이 코드 하나만으로 team이름 까지 조회가능해짐.

//            log.info("MemberId에 해당하는 team name? : {}", team.getName());
            // 내부객체 참조해서 getName() 으로 꺼내기만 하면된다.  nonPersist() 처럼 별도로직 필요없다.
            log.info("MemberId에 해당하는 user name? : {}", member.getUsername());
            log.info("MemberId에 해당하는 team name? : {}", member.getTeam().getName());

            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Test
    @DisplayName("양방향 연관관계")
    void eachPersist() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // MbrEach, TeamEach로 만들어 사용
//            TeamEach teamEach = em.find(TeamEach.class, "402");
            /*TeamEach teamEach = new TeamEach();
            teamEach.setName("teamOK1");
            em.persist(teamEach);*/
            TeamEach teamEach = em.find(TeamEach.class, "1");

            MbrEach member = new MbrEach();
            member.setUsername("monami5");
            member.setTeamEach(teamEach); // 변수대신 객체로 set
            em.persist(member);

            em.flush();
            em.clear();

            MbrEach findMember = em.find(MbrEach.class, member.getId());
            List<MbrEach> findMembers = findMember.getTeamEach().getMbrEach(); // findMember 가 속한 팀의 모든 맴버들

            for (MbrEach m : findMembers) {
                System.out.println("MbrEach 양방향참조 Member : "+ m.getUsername());
            }

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
