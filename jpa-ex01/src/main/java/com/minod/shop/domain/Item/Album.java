package com.minod.shop.domain.Item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("A") // 싱글테이블 전략시 생기는 컬럼에서 멀로 출력할지 정하는 것.
@Getter
@Setter
public class Album extends Item {
    private String artist;
    private String etc;
}