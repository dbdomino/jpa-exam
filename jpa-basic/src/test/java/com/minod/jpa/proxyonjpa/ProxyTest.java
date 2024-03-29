package com.minod.jpa.proxyonjpa;

import com.minod.jpa.domain.Team;
import com.minod.jpa.domain.양방향관계.MbrEach;
import com.minod.jpa.domain.양방향관계.TeamEach;
import com.minod.jpa.repository.JpaTestRepository;
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

//    @Test
    @DisplayName("조회시 내부 참조한 team도 같이 select하게 됨.")
    void Find01() {
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

    @Test
    @DisplayName("getReference 이해하기")
    void proxyJpatest01() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            Team teamA = new Team();
            teamA.setName("momo1");
            Team teamB = new Team();
            teamB.setName("momo2");
            System.out.println("teamA TeamB == 비교 : "+(teamA.getClass() == teamB.getClass()));
            System.out.println(teamA.getClass()); // teamEach가 null 이면 조회시 에러남.
            System.out.println(teamB.getClass());

            MbrEach findMember1 = em.find(MbrEach.class, 102); // 이거만 수행해도 하이버네이트에서 select 일어남
            MbrEach findMember2 = em.getReference(MbrEach.class, 152); // 이거만 수행한다고 select 일어나지 않음 프록시 객체를 불러오는 것이다.

            System.out.println("---------object");
            extracted(findMember1, findMember2); // 참조 타입 같은 MbrEach 이나 비교하면 false나옴, 타입이 다르다면 어째서인가? findMember2는 프록시객체로 나왔기 때문이다.
//            System.out.println(findMember1.getClass()); // teamEach가 null 이면 조회시 에러남.
//            System.out.println(findMember2.getClass());
            System.out.println("---------object");

            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            log.info("error occure ", e);
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void extracted(MbrEach findMember1, MbrEach findMember2) {
        System.out.println("member1 member2 == 비교 : "+(findMember1.getClass() == findMember2.getClass()));
    }

    @Test
    @DisplayName("getReference 프록시 객체끼리 비교")
    void proxyJpatest02() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            MbrEach findMember1 = em.getReference(MbrEach.class, 102); // 클래스 같고, PK 같으면
            MbrEach findMember2 = em.getReference(MbrEach.class, 102); // 1차캐시에서 같은 객체를 반환해준다.

            System.out.println("---------object");
            extracted(findMember1, findMember2);
            System.out.println(findMember1.getClass()); // teamEach가 null 이면 조회시 에러남.
            System.out.println(findMember2.getClass());
            System.out.println("---------object");

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
    @DisplayName("getReference 프록시 객체 먼저 만들었을 때 비교, 특이하네...")
    void proxyJpatest03() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            MbrEach findMember1 = em.getReference(MbrEach.class, 102); // 클래스 같고, PK 같으면
            MbrEach findMember2 = em.find(MbrEach.class, 102); // 1차캐시에서 같은 객체를 반환해준다.
            // find인데 select 수행되었지만 클래스 같고 PK 같으면 같은 객체를 1차캐시에서 true로 반환해야 한다는 특징이 있다.
            // 이에 맞추기 위해 영속성컨택스트에서 findMember2에 프록시 객체를 줘버린다.
            // 어짜피 프록시객체로 뱉더라도 안에 속성값 읽을 수 있으면 되는거라 크게 상관없음.

            System.out.println("---------object");
            extracted(findMember1, findMember2);
            System.out.println(findMember1.getClass()); // teamEach가 null 이면 조회시 에러남.
            System.out.println(findMember2.getClass());
            System.out.println("---------object");

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
