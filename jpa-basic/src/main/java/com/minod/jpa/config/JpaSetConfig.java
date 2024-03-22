package com.minod.jpa.config;

import com.minod.itemservice.repository.ItemRepository;
import com.minod.itemservice.repository.jpa.JpaItemRepository;
import com.minod.itemservice.service.ItemService;
import com.minod.itemservice.service.ItemServiceV1;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaSetConfig {

    private final EntityManager em; // 길어서 em으로 많이 쓴다고 함.
    public JpaSetConfig(EntityManager em) {
        this.em = em;
    }

}
