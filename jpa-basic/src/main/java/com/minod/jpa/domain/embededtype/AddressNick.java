package com.minod.jpa.domain.embededtype;

import jakarta.persistence.*;

// 임베디드 타입 객체에 연관관계를 맺기
@Entity
public class AddressNick {
    @Id
    @GeneratedValue
    private Long id;
    private String AddressNick;
    public AddressNick() {

    }
}
