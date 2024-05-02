package com.minod.jpa.embededtype;

import com.minod.jpa.domain.embededtype.AddressC;
import com.minod.jpa.domain.embededtype.MemberEmbeded;
import com.minod.jpa.domain.embededtype.PeriodC;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class EmbededTypeNoChangeTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

//    @Test
    void embededChangeTest() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            MemberEmbeded member = em.find(MemberEmbeded.class, memberId);

            AddressC address = new AddressC("city7", "street", "220745");
            MemberEmbeded member = new MemberEmbeded();
            member.setUsername("member06");
            member.setAddress(address);
            member.setPeriod(new PeriodC());

            MemberEmbeded member2 = new MemberEmbeded();
            member2.setUsername("member07");
            member2.setAddress(address);
            member2.setPeriod(new PeriodC());

            em.persist(member); // 맴버객체 하나만 persist 하고 수정이 일어나면? update도 한번 일어남
            em.persist(member2); // 맴버객체 2개를 persist 하고 수정이 일어나면? update도 두번 일어남 (member에 대한 수정, 그리고 member2에 대한 수정)
//            member.getAddress().setCity("change city5"); // 임베디드 타입의 값을 바꿈
            AddressC addressV2 = new AddressC("city88", "street8", "22078");
            member.setAddress(addressV2);
            // member 안의 임베디드 타입의 속성값을 setter 변경하면 member의 해당 컬럼의 값이 바뀌게 되는데,
            // 왜 member2의 update까지 일어나는 것인가? (이건 하이버네이트에서 발생하는 버그이며, 찾기 어려운 버그다. 임베디드 타입을 쓰다보니 생긴 사이드 이팩트라고 보면 된다.)
            // 원인은 member 엔티티, member2엔티티 에 address라는 하나의 공유 객체로 setAddress(address) 참조시켰기 때문이다. address 객체값이 변경됬는데, member 랑 member2가 같은 객체를 참조하고 있으니까, 결국 테이블의 update까지 같이 발생하게 되버림

            // 만약 이런 기능을 의도한거라면 임베디드 타입을 값 타입으로 쓸게 아니라, Entity를 통해서 address를 참조해서 쓰는식으로 Entity설계를 해야 한다고 한다.
            // 값 타입으로 쓴다면 위 처럼 예상치 못한 효과가 생기면 안된다.(객체를 값타입으로 쓰려니 발견된 예상치 못한 효과)

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
    void embededCopyTest() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            MemberEmbeded member = em.find(MemberEmbeded.class, memberId);

            AddressC address = new AddressC("city8", "street8", "22078");
            MemberEmbeded member = new MemberEmbeded();
            member.setUsername("member08");
            member.setAddress(address);
            member.setPeriod(new PeriodC());

            // address 의 값을 복사해서 새객체를 만들어 set하는 전략으로 해야 한다. 임베디드 타입을 여러 entity 객체에 공유해서 setter로 넣는 건 불필요한 변경이 생길 수 있다.
            AddressC address2 = new AddressC(address.getCity(), address.getStreet(), address.getZipcode());

            MemberEmbeded member2 = new MemberEmbeded();
            member2.setUsername("member09");
            member2.setAddress(address2); // 새로만든 address 객체로 값 입력
            member2.setPeriod(new PeriodC());

            em.persist(member); // 맴버객체 하나만 persist 하고 수정이 일어나면? update도 한번 일어남
            em.persist(member2); // 맴버객체 2개를 persist 하고 수정이 일어나면? update도 두번 일어남 (member에 대한 수정, 그리고 member2에 대한 수정)
//            member.getAddress().setCity("change city8"); // 임베디드 타입의 값을 바꿈, // 불변객체로 바꾼다면 이거 사용 불가능함.
            AddressC addressV2 = new AddressC("city88", "street8", "22078");
            member.setAddress(addressV2);

            // member 안의 임베디드 타입의 속성값을 setter 변경하면 member의 해당 컬럼의 값이 바뀌게 되는데,
            // 왜 member2의 update까지 일어나는 것인가? (이건 하이버네이트에서 발생하는 버그이며, 찾기 어려운 버그다. 임베디드 타입을 쓰다보니 생긴 사이드 이팩트라고 보면 된다.)
            // 원인은 member 엔티티, member2엔티티 에 address라는 하나의 공유 객체로 setAddress(address) 참조시켰기 때문이다. address 객체값이 변경됬는데, member 랑 member2가 같은 객체를 참조하고 있으니까, 결국 테이블의 update까지 같이 발생하게 되버림

            // 만약 이런 기능을 의도한거라면 임베디드 타입을 값 타입으로 쓸게 아니라, Entity를 통해서 address를 참조해서 쓰는식으로 Entity설계를 해야 한다고 한다.
            // 값 타입으로 쓴다면 위 처럼 예상치 못한 효과가 생기면 안된다.(객체를 값타입으로 쓰려니 발견된 예상치 못한 효과)

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
