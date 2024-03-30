package com.minod.jpa.domain.datacollectiontype;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable // 임베디드 타입, 테이블만들지 않음.
public class AddressHistory {
    private String city;
    private String street;
    private String zipcode;

    public AddressHistory() {
    }

    public AddressHistory(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressHistory that = (AddressHistory) o;
        return Objects.equals(city, that.city) && Objects.equals(street, that.street) && Objects.equals(zipcode, that.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}
