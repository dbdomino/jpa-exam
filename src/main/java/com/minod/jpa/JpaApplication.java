package com.minod.jpa;

import com.minod.itemservice.TestDataInit;
import com.minod.itemservice.config.JpaConfig;
import com.minod.itemservice.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.minod.jpa") // 오오... 어플리케이션 스캔 범위도 설정가능.
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	@Profile("local")
	public TestDataInit testDataInit(ItemRepository itemRepository) {
		return new TestDataInit(itemRepository);
	}


}
