package com.minod.jpa.cascade;

import com.minod.jpa.domain.cascade.Child;
import com.minod.jpa.domain.cascade.Parent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class JpaCascadeTest {
    @PersistenceUnit
    private EntityManagerFactory emf;


    @Test
    @DisplayName("일반적인 엔티티에서 insert날리기")
    void cascadeTest() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
            Child child1 = new Child();
            child1.setName("샤샤19");
            Child child2 = new Child();
            child2.setName("그리모20");

            Parent parent = new Parent();
//            parent.addChild(child1); // child.setParent() 소스가 포함되고, parent.childList.add() 도 포함되는 커스텀 메서드,
//            parent.addChild(child2);

            // 객체마다 persist 날려줘야한다. 누군가는 이걸 귀찮다고 한다.
            // 또한 parent 중심인지 child 중심인지 구분도 안된다.(연관관계의 주인은 child이다만 왜 자식이 주인이냐? 딜레마네)
            // child가 persist될 때, child 테이블에 parent컬럼이 추가되어 들어가진다. 양방향 연관관계 덕분이다.
//            em.persist(parent); // 이거만 해도 에러는 안남, parent테이블에만 insert됨, 다만 child1 child2는 persist에 안들어가서 child 테이블에 영속화 되지않음.
//            em.persist(child1);
//            em.persist(child2);

            // 여기서 em.persist(child) 없이 em.persist(parent)만으로 child에도 insert됙 하고싶다. 더 편하게 하고싶다 그러면 어떻게 해야할까?
            // parent Entity에 참조하는 child 관계 어노테이션에다 cascade=CascadeType.ALL 이라는 옵션을 참조에다 붙이면, 어떻게 될까?
            parent.getChildList().add(child1);
            parent.getChildList().add(child2);
            child1.setParent(parent);
            child2.setParent(parent);
            em.persist(parent);
            // parent 의 childList에 add하는것, 그리고 persist(parent) 한 것 만으로, child 테이블에도 insert가 일어나게 된다.
            // parent 객체에 포함된 child List의 정보를 기반을 읽어들여 child 테이블에도 insert 하게 됨. cascade=CascadeType=ALL 옵션 덕에 이게 가능해짐.
            // cascade ALL 옵션 안붙이면 불가능했음.

            parent.getChildList().remove(0); // parent 객체에 참조된 리스트의 데이터를 제거, 이것만으로 entity 에 delete 쿼리 날리는게 가능해진다.
            // 안그러면 보통 em.remove(child1) 이런식으로 써야 할 것이다.
            // 객체 지향적인 코드로 보고싶다면 쓰는것도 좋아보이지만 난 반댈세, 위험요소가 많아보인다.


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
