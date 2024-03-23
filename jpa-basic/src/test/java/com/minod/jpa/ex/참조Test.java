package com.minod.jpa.ex;

import com.minod.jpa.domain.ex.Team;
import com.minod.jpa.domain.ex.TeamMember;
import com.minod.jpa.repository.JpaSaveRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class 참조Test {
    @Autowired
    private JpaSaveRepository repository;
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @DisplayName("참조를 TeamMember에 추가해서 사용하는 방식")
    void Ex03(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작
        try {
            // 팀 추가
            Team team = new Team();
            team.setName("TeamX");
            em.persist(team);

            // 멤버 추가
            TeamMember member1 = new TeamMember();
            member1.setUsername("X");
            member1.setTeam(team);
            em.persist(member1);

            //---------------
            // 영속성 컨택스트에서 find해서 가져오는게아니라 db에서 가져오도록 하려면 flush() 한번해주면 된다.
            // 즉 db에서 select하는걸 아래 find에서 보고싶으면 flush, clear추가
            em.flush(); // 강제호출
            em.clear(); // 영속성 컨택스트 초기화

            //---------------
            // 멤버 조회 하나로 teamName 가져오기 가능
            TeamMember findMember = em.find(TeamMember.class, member1.getId()); // 여기서 find한건 영속성 컨택스트 1차캐시에서 가져온 것이다.
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = "+findTeam.getName());
            System.out.println("findTeam = "+findTeam.getName());

            // 멤버에 team 수정
            Team newTeam = em.find(Team.class, 102);
            findMember.setTeam(newTeam);

            tx.commit(); // 트랜잭션 종료

        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
        } finally {
            em.close();
        }
    }

/*
    // 객체를 테이블에 맞추어 데이터 중심으로 만든 예제
    // TeamMember의 TEAM_ID 외래키를 그대로 값으로 다룰때 사용하는 예제
    @Test
    @DisplayName("참조없이 외래키 그대로 사용하는 방식")
    void Ex02(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // 참조 대신 외래키를 그대로 사용하는 것. (Entity만 봐도 참조없이 키만 잇음)
            // 객체를 테이블에 맞추어 데이터 중심으로 모델링하면, 위 쿼리를 구현하기 위해 다음처럼 로직이 만들어져야 한다.
            // findMember가 어느 팀 소속인지 알고싶다면?(팀 이름을 알고싶다면), teamName을 알고싶다면?
            // select * from team_member a join team t on a.team_id = t.team_id;  참조된 두 테이블을 같이 보기 위해서 쿼리는 이런 쿼리가 될 것인데,
            TeamMember findMember = em.find(TeamMember.class, 2); // 2는 MemberID이다

            Long teamId = findMember.getTeamId(); // findMember가 어느팀인지 찾았네.

            Team findTeam = em.find(Team.class, 1); // 어느팀인지 찾은걸로 team테이블에서 꺼낸다.
            findTeam.getName(); // 이게 원하는 답

            // 어느팀 소속인지 findMember 에 적힌걸 그대로 쓰면 되는데 굳이 왜 team테이블까지 조회하는가?
            // select * from team_member a join team t on a.team_id = t.team_id;  이 쿼리를 유지하기 위해서다. (즉 테이블의 관계를 보장하기 위해, 연관 관계를 보장하기 위해)
            // db에 테이블의 연관관계를 구현하려면 다음처럼 값을 뽑아내고, 객체만들고 이런식을 써야 하는데, 딱 봐도 객체를 이용하는 것처럼 보이긴 하나, 협력관계로 보이지 않는다.
            // 필요한 객체 생성해서 값만 뽑아 쓰는데, 이건 협력/참조 는 아니다.
            // 애초에 teamName을 DB에 저딴식으로 보관해서 그런건데, 어쩔수 없다 db의 효율을 위해서니까.

            log.info("-----------------------------");
            System.out.println("findTeam = "+findTeam);
            System.out.println("findMember = "+findMember);

            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
        } finally {
            em.close();
        }
    }

//    @Test
    @DisplayName("테이블에 값 저장")
    void Ex01(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            TeamMember member1 = new TeamMember();
            member1.setUsername("A");
            TeamMember member2 = new TeamMember();
            member2.setUsername("B");
            TeamMember member3 = new TeamMember();
            member3.setUsername("C");

            // member1을 TeamA에 넣고 싶다.
            // 원래라면 team 테이블에서 외래키인 team_id를 select, 가져온걸로 team_member 테이블에 update진행하면 된다. 이걸 객체지향 처럼 db를 다루면 아래처럼 된다.

            member1.setTeamId(team.getId());


            // 객체를 테이블에 맞추어 데이터 중심으로 모델링하면, 협력 관계를 만들 수 없다.


            em.persist(member1);


            log.info("-----------------------------");
            System.out.println("team = "+team);
            System.out.println("member1 = "+member1);
            System.out.println("member2 = "+member2.getId());
            System.out.println("member3 = "+member3.getId());

            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
        } finally {
            em.close();
        }
    }

 */
}
