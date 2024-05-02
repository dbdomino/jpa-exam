package com.minod.jpa.inherit;

import com.minod.jpa.domain.inherit.joined.Item;
import com.minod.jpa.domain.inherit.joined.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@Slf4j
@SpringBootTest
public class Inherit {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    void inheritTest_JOINED(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // super타입과 sub타입의 관계인 테이블을 이런식으로 매핑하면, 다음처럼 결과가 생긴다.
            // Item 클래스에 @Inheritance(strategy= InheritanceType.JOINED) 로 하고 테스트 해야함.
        Movie movie = new Movie();
        movie.setDirector("디렉터5");
        movie.setActor("배우5");
        movie.setName("영화이름5");
        movie.setPrice(15000);

        em.persist(movie); // movie 등록 하나로 item 테이블에 먼저 insert날리고, 그 뒤에 movie table에 insert 또 날린다.

            Movie findMovie = em.find(Movie.class, 52);
            System.out.println("find Movie 조회한것 : "+findMovie);

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
    void inheritTest_SINGLE_TABLE(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // super타입과 sub타입의 관계인 테이블이 아닌 하나의 Itea 테이블에 모든 하위테이블의 컬럼을 다넣어서 사용( 단순할경우 이렇게 쓰기도 한다고함. 대신 null 허용)
            // Item 클래스에 @Inheritance(strategy= InheritanceType.SINGLE_TABLE) 로 하고 테스트 해야함.
            Movie movie = new Movie();
            movie.setDirector("디렉터5");
            movie.setActor("배우5");
            movie.setName("영화이름5");
            movie.setPrice(15000);

            em.persist(movie); // movie 등록 하나로 item 테이블에 먼저 insert날리고, 그 뒤에 movie table에 insert 또 날린다.

            Movie findMovie = em.find(Movie.class, 52);
            System.out.println("find Movie 조회한것 : "+findMovie);

            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            System.out.println("에러");
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

//    @Test
    void inheritTest_TABLE_PER_CLASS(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            // super타입과 sub타입의 관계인 테이블을 이런식으로 매핑하면, 다음처럼 결과가 생긴다.
//        Movie movie = new Movie();
//        movie.setDirector("디렉터3");
//        movie.setActor("배우3");
//        movie.setName("영화이름3");
//        movie.setPrice(13000);

//        em.persist(movie); // movie 등록 하나로 item 테이블에 먼저 insert날리고, 그 뒤에 movie table에 insert 또 날린다.

            Movie findMovie = em.find(Movie.class, 52);
            System.out.println("find Movie 조회한것 : "+findMovie);
            // JPA 엔티티의 상속 전략을 TABLE_PER_CLASS 로 했을 때 문제점
//            Item findMovie2 = em.find(Item.class, movie.getId()); // 객체적으로 본다면 부모객체인 Item으로도 참조가 가능해야 한다.
            Item findMovie2 = em.find(Item.class, 102); // 객체적으로 본다면 부모객체인 Item으로도 참조가 가능해야 한다.
            System.out.println("find Movie2 조회한것 : "+findMovie2); // movie객체로 불러온게 있다면 1차캐시에서 뒤져서 빠르게 참조 가능함. 그러나 이게 최초라면?
            // 부모타입으로 최초로 find 했을 경우 select 요청을 하는데 item 타입과 하위 타입들을 union all로 쿼리가엄청 복잡하게 되버린다.
            // 부모타입으로 참조시키려면 문제가 생길 가능성이 있다. TABLE_PER_CLASS 전략 (애초에 Entity설계를 부모타입 자식타입이 아닌, 각 테이블만의 단위로 만들면 문제없겠지만 상속 전략을 쓰면 문제된다.)

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
