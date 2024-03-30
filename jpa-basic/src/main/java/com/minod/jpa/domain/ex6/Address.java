package com.minod.jpa.domain.ex6;

import java.util.Objects;

//@Embeddable
public class Address {
    // 불변객체로 애초에 만들기
    private String city;
    private String street;
    private String zipcode;

    public Address() {
    }

    public Address(String city, String street, String zipcode) {
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Address address = (Address) o;
//        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getZipcode(), address.getZipcode());
    }// 위에 바로 불러와서 비교하는 것과 여기 getter로 비교하는 것 중에서 getter로 비교하도록 하는걸 선택해서 자동완성 해야한다.
    // proxy를 통할 경우 getter로 해야 equals() 결과가 제대로 나온다고 한다.

//    @Override
//    public int hashCode() {
//        return Objects.hash(city, street, zipcode);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }// 위에 바로 불러와서 비교하는 것과 여기 getter로 비교하는 것 중에서 getter로 비교하도록 하는걸 선택해서 자동완성 해야한다.
    // proxy를 통할 경우 getter로 해야 hashCode() 결과가 제대로 나온다고 한다.
}
