package com.minod.shop.domain.valuetype;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable // 값타입으로 선언
@Getter // 값타입은 변경불가능하게 설계해야 하므로 Setter 제거
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // JPA 스펙상 값 타입은 자바 기본 생성자를 Protected로 설정하는게 안전하다..
    protected Address() {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
