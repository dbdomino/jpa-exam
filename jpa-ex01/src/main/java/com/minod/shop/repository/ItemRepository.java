package com.minod.shop.repository;

import com.minod.shop.domain.Item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/** void save(Item item)
 *  List<Item> findAll()
 */
@Repository
@RequiredArgsConstructor   // final된건 생성자주입 자동생성 시켜줌
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item); // id가 없으면 item 신규생성
        } else {
            em.merge(item); // id 있으면 item 수정, 수정사항을 영속성컨텍스트에서 db로 적용수행
        }
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList(); // JPQL
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

}
