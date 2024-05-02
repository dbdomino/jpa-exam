package com.minod.jpa.proxyonjpa.lazyloading;

import com.minod.jpa.domain.lazyloading.MbrLazyLoading;
import com.minod.jpa.domain.lazyloading.TeamLazyLoading;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class LazyLoadingTest {
    @PersistenceUnit
    private EntityManagerFactory emf;
    @Test
    void fetchType_Find() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            TeamLazyLoading team = new TeamLazyLoading();
//            team.setName("manoMo1");
//            em.persist(team);

//            TeamLazyLoading findTeam = em.find(TeamLazyLoading.class, 2L);

//            MbrLazyLoading member = new MbrLazyLoading();
//            member.setUsername("hohoV2_2");
//            member.setTeamEach(findTeam); // 팀 참조추가, 없으면 null로 등록됨.
//            em.persist(member);


            // 참조 @ManyToOne 에 FetchType.LAZY 쓰니까 member만 그대로 조회되었음., FetchType.EAGER로 바꿔가며 테스트 진행
            MbrLazyLoading findMember = em.find(MbrLazyLoading.class, 2L);
            log.info("findMember -> getUsername : {}", findMember.getUsername());
//            log.info("findMember -> getTeamId : {}", findMember.getTeam_Id()); // team_id 보려면 참조되는 객체를 통해서 조회해야 함.

            System.out.println("----------then");
            printMemberAndTeam(findMember);  // LAZY 로 FetchType을 설정하면, Team객체를 불러오는 명령이 들어갔을 때, 프록시 객체를 통해서 Team객체를 select해온다.
//            printMember(findMember);  // 이걸로하면 member 정보만 출력


            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
        } finally {
            em.close();
        }
    }
    private void printMemberAndTeam(MbrLazyLoading member) {
        // 조회시 Member 객체와 Team 객체를 한번에 조회하는 기능을 메서드로 만듬. 참조를 통해 team을 받아옴.
        String memberName = member.getUsername();
        System.out.println("username = "+ memberName );

        TeamLazyLoading team = member.getTeamEach();
        System.out.println("team = "+team.getName());
    }
    private void printMember(MbrLazyLoading member) {
        // 조회시 Member 객체만 조회하려면?
        String memberName = member.getUsername();
        System.out.println("username = "+ memberName );

//        TeamEach team = member.getTeamEach();
//        System.out.println("team = "+team.getName());
    }
}
