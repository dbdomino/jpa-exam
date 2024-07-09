package com.minod.shop.service;

import com.minod.shop.domain.Item.Item;
import com.minod.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/** void saveItem(Item item)
 *  Item findOne(Long itemId)
 *  List<Item></Item> findItems(Long itemId)
 */
@Service
@Transactional(readOnly = true) // 조회되는 메서드 성능향상을 위해
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne( itemId );
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Transactional
    public void updateItem(Item item) {   itemRepository.save(item);    }
}
