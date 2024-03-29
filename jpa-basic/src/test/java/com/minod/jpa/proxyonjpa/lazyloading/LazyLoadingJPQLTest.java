package com.minod.jpa.proxyonjpa.lazyloading;

import com.minod.jpa.domain.lazyloading.MbrLazyLoading;
import com.minod.jpa.domain.lazyloading.TeamLazyLoading;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class LazyLoadingJPQLTest {
    @PersistenceUnit
    private EntityManagerFactory emf;
    @Test
    void fetchType_Find() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            TeamLazyLoading team = new TeamLazyLoading();
//            team.setName("manoMo5");
//            em.persist(team);

//            TeamLazyLoading findTeam = em.find(TeamLazyLoading.class, 1L);

//            MbrLazyLoading member = new MbrLazyLoading();
//            member.setUsername("hoho1");
//            member.setTeamEach(findTeam); // 팀 참조추가, 없으면 null로 등록됨.
//            em.persist(member);


            // JPQL로 fetch=FetchType.EAGER 조회할 때 select가 2번 나가게 된다. 둘다 하이버네이트가 자동으로 만든 쿼리다.
            // 1. JPQL 이 번역한 쿼리
            // select
            //            mll1_0.id,
            //            mll1_0.team_id,
            //            mll1_0.username
            //        from
            //            public.mbr_lazy_loading mll1_0
            // 로 Member를 가져옴.
            // LAZY 옵션이면 나중에 프록시로 값을 집어넣으면 되지만, EAGER 옵션이면 즉시 값이 다 들어가 있어야된다. 즉 team_id말고, team_name도 준비가되어야된다.
            // 때문에 한번더 select 날린다.
            //select
            //        tll1_0.team_id,
            //        tll1_0.name
            //    from
            //        public.team_lazy_loading tll1_0
            //    where
            //        tll1_0.team_id=?
            // em.find로 검색할 땐, pk를 지정해서 검색하는 것이므로 jpa가 매핑을 자동으로 해서 쿼리를 만들어준다.
            // JPQL로 쿼리를 만드는건 사용자가 직접 JPQL 문으로 쿼리를 만드는 것이다.(JPA도 만능은 아니니까 복잡한 쿼리는 직접 만들어야함.)
            //// 쿼리보면 pk구분없이 모두 조회다. 그럼 member에서 필요한 team만큼 n번 더 select가 발생한다.
            //// 두번째 sql 보면 tll1_0.team_id=? 이렇게 조건이 들어간다. 조건이 되는 값은 member에서 필요해지는 teamId의 개수만큼일 것이다.
            // member에 들어있는 team_id 개수가 N개일 때, 정리해서 아래 em.createQuery가 실행될 때 발생하는 select가 JPQL 번역한 sql 1개와 참조된거 조회하는 쿼리N개
            // 합쳐서 1+N개가 되는게 1+N 문제다.   Lazy 전략으로만 한다고해서 문제가 해결되는 건 아닌데, 대부분 해결은 된다. (
            // 1. JPQL fetch join (fetch 조인을 써서 같이 값을 가져올 때만 join으로 값을 같이 가져오도록 하는 것, 처음부터 select 할때 join해서 가져오도록 하는 방법임. eager전략이니까, lazy로 설정해놔도 JPQL에 fetch join으로 실행하면 join되어 즉시 한번에 다 불러온다. )
            // 2. @EntityGraph 라는 어노테이션으로 해결함. (주로 fetch join 으로 해결함.)
//            List<MbrLazyLoading> select1 = em.createQuery("select m from MbrLazyLoading m where id=2", MbrLazyLoading.class)
            List<MbrLazyLoading> select1 = em.createQuery("select m from MbrLazyLoading m join fetch m.team", MbrLazyLoading.class)
//            List<MbrLazyLoading> select1 = em.createQuery("select m from MbrLazyLoading m", MbrLazyLoading.class)
                            .getResultList();


            System.out.println("----------then");
//            printMemberAndTeam(findMember);  // LAZY 로 FetchType을 설정하면, Team객체를 불러오는 명령이 들어갔을 때, 프록시 객체를 통해서 Team객체를 select해온다.
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
