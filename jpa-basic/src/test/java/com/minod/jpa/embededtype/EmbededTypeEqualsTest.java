package com.minod.jpa.embededtype;

import com.minod.jpa.domain.embededtype.AddressC;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class EmbededTypeEqualsTest {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    void embededEqualsTest() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻어옴.
        tx.begin(); // 트랜잭션 시작

        try {
//            MemberEmbeded member = em.find(MemberEmbeded.class, memberId);

            AddressC address1 = new AddressC("city7", "street", "220745");
            AddressC address2 = new AddressC("city7", "street", "220745");
            AddressC address3 = new AddressC("city9", "street", "220745");

            System.out.println("address1.equals(address2) : "+address1.equals(address2));
            System.out.println("address2.equals(address3) : "+address2.equals(address3));



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
