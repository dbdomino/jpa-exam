package com.minod.jpa.config;

import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaSetConfig {

    private final EntityManager em; // 길어서 em으로 많이 쓴다고 함.
    public JpaSetConfig(EntityManager em) {
        this.em = em;
    }



}
