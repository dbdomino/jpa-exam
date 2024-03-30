package com.minod.jpa.datacollection;

import com.minod.jpa.domain.datacollectiontype.AddressHistory;
import com.minod.jpa.domain.datacollectiontype.MemberCollection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
class MemberCollectionTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

//    @Test
    void MemberCollectionTest() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            MemberCollection member = new MemberCollection();
            member.setUsername("user1");

            // 값 컬랙션에 데이터 추가하기  --> db에선 값 컬렉션 테이블에 insert 될 것이다.
            member.getFavoriteFoods().add("1라면");// 값을 set에 저장
            member.getFavoriteFoods().add("2비빔면");
            member.getFavoriteFoods().add("3국수");

            member.getAddressHistories().add(new AddressHistory("주소1-1", "주소1-2", "22324"));
            member.getAddressHistories().add(new AddressHistory("주소2-1", "주소2-2", "22324"));

            em.persist(member);
            // 여기 persist 한번으로 member테이블, FavoritFood 테이블, AddressHistory 테이블에 insert완료되었다.
            // 연결 관계를 맺지 않고도 이런 전략이 가능하다.

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
    void MemberCollectionFindTest() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            member.getFavoriteFoods().add("1라면");// 값을 set에 저장
//            member.getFavoriteFoods().add("2비빔면");
//            member.getFavoriteFoods().add("3국수");
//
//            member.getAddressHistories().add(new AddressHistory("주소1-1", "주소1-2", "22324"));
//            member.getAddressHistories().add(new AddressHistory("주소2-1", "주소2-2", "22324"));

            // 조회 예시로 테스트
            MemberCollection member = em.find(MemberCollection.class, 1L); // member 테이블만 조회하게 됨.
            // 컬렉션들은 OneToMany로 잡힌 관계이다보니 지연로딩이 기본이다.
            System.out.println("-----------------");
            System.out.println("member username: "+member.getUsername()); // 그러므로 이거 수행시
            System.out.println("------getFavoriteFoods--------");
            System.out.println(member.getFavoriteFoods().toString()); // 지연로딩으로 FavoiriteFoods 테이븖 조회됨
            System.out.println("------getAddressHistories--------");
            member.getAddressHistories().stream().forEach(ss -> System.out.println(ss.getCity())); // 지연로딩으로 AddressHistory 테이블 조회됨



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
    void MemberCollectionUpdateTest() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            member.getFavoriteFoods().add("1라면");// 값을 set에 저장
//            member.getFavoriteFoods().add("2비빔면");
//            member.getFavoriteFoods().add("3국수");
//
//            member.getAddressHistories().add(new AddressHistory("주소1-1", "주소1-2", "22324"));
//            member.getAddressHistories().add(new AddressHistory("주소2-1", "주소2-2", "22324"));

            // 조회 예시로 테스트
            MemberCollection member = em.find(MemberCollection.class, 1L); // member 테이블만 조회하게 됨.
            // 수정시 값 타입들은 immutable 불변객체로 설계하는게 추천방식이다.
            System.out.println("-----------------");
            System.out.println("member username: "+member.getUsername()); // 그러므로 이거 수행시
            System.out.println("------getFavoriteFoods--------");
            Set<String> favoriteFoods = member.getFavoriteFoods();
            // 1라면 을 1짬뽕으로 바꾸려면?
            // 안타깝지만 String타입이라 변경이 안된다. remove후에 새로 add하는 수 밖에 없다.
            favoriteFoods.remove("1라면");
            favoriteFoods.add("1짬뽕"); // set이다보니 중복 등록이 안되어서 2중등록 방지도 가능하네... 이야 기가막히다.

            System.out.println("------getAddressHistories--------");
//            member.getAddressHistories().stream().forEach(ss -> System.out.println(ss.getCity())); // 지연로딩으로 AddressHistory 테이블 조회됨
            List<AddressHistory> addressList = member.getAddressHistories();
            // 여기서도 값 타입 객체의 수정을 위해 기존 값을 제거 후 new addressHistory 객체를 넣어줘야 한다.
            // 컬랙션은 remove 같은걸 찾을 때 equals()를 수행해서 비교한다. 값을 비교하도록 equals()를 재정의한다.
            // equals 재정이 되면, 값의 비교로 같은지 비교한다. 찾길 원하는 같은 값인 객체를 넣어주면, 같은걸로 찾아 remove 할 수 있다.

            addressList.remove(new AddressHistory("주소1-1", "주소1-2", "22324"));
            addressList.add(new AddressHistory("주소1-5", "주소1-5", "54321"));


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