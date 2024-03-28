package com.minod.jpa.proxyonjpa;

import com.minod.jpa.domain.양방향관계.MbrEach;
import com.minod.jpa.domain.양방향관계.TeamEach;
import com.minod.jpa.repository.JpaTestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ProxyTest {
    @Autowired
    private JpaTestRepository repository;
    @PersistenceUnit
    private EntityManagerFactory emf;

//    @Test
    void find1() {repository.find1();}

//    @Test
    void setting_Find() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            TeamEach team = new TeamEach();
//            team.setName("manoMo1");
//            em.persist(team);

            TeamEach findTeam = em.find(TeamEach.class, 1L);

            MbrEach member = new MbrEach();
            member.setUsername("hoho4");
            member.setTeamEach(findTeam); // 팀 참조추가, 없으면 null로 등록됨.

            em.persist(member);

            MbrEach findMember = em.find(MbrEach.class, member.getId());
            log.info("findMember -> MbrEach : {}", findMember.toString());

            printMemberAndTeam(findMember);  // 이걸로하면 한번에 참조된 team정보까지 출력 가능


            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private void printMemberAndTeam(MbrEach member) {
        // 조회시 Member 객체와 Team 객체를 한번에 조회하는 기능을 메서드로 만듬. 참조를 통해 team을 받아옴.
        String memberName = member.getUsername();
        System.out.println("username = "+ memberName );

        TeamEach team = member.getTeamEach();
        System.out.println("team = "+team.getName());
    }
    private void printMember(MbrEach member) {
        // 조회시 Member 객체만 조회하려면?
        String memberName = member.getUsername();
        System.out.println("username = "+ memberName );

//        TeamEach team = member.getTeamEach();
//        System.out.println("team = "+team.getName());
    }

    @Test
    void easyFind() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 2줄만으로 Member Id가 52인 조건의 member값과 Team값을 조회하는 메서드 사용가능, 근데 member만 조회하려면?
            // MbrEach는 참조관계 때문에 find하면 join되어서 team이랑 같이 읽어버림.(자원낭비)
            // 참조되었지만, 조건이 맞다면 member만 select 하는 방법은? JPA 프록시 이해해야함.
            MbrEach findMember = em.find(MbrEach.class, 52);
//            printMemberAndTeam(findMember);
            printMember(findMember);

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
