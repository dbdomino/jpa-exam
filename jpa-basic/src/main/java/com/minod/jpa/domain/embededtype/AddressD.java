package com.minod.jpa.domain.embededtype;

import jakarta.persistence.Embeddable;

// 임베디드 타입 안에 임베디드 타입도 넣기 가능.
@Embeddable
public class AddressD {
    String region;
    String national;
    String language;

    public AddressD() {
    }

    public AddressD(String region, String national, String language) {
        this.region = region;
        this.national = national;
        this.language = language;
    }

    public String getRegion() {
        return region;
    }

    public String getNational() {
        return national;
    }

    public String getLanguage() {
        return language;
    }
}
