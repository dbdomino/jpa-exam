package com.minod.jpa.domain.datacollectiontype;

import jakarta.persistence.Embeddable;

@Embeddable // 임베디드 타입, 테이블 만들지 않음.
public class FavoritFood {
    private String foodName;
}
