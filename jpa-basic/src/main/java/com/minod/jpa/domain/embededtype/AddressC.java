package com.minod.jpa.domain.embededtype;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

// 값 타입을 정의하는 임베디드 타입으로 쓰기위한 클래스라고 JPA에 알려주기 위해 사용
@Embeddable // 사용할곳에 @Embedded만 쓰던지, 여기에@Embeddable 만 쓰던지 둘중하나만 써도되나, 둘다 써두는게 구분이 편해서 권장
public class AddressC {
    String city;
    String street;
    @Column(name="zipc")
    String zipcode;

//    @Embedded
//    private AddressD addressD; // 임베디드 타입에 임베디드 타입을 넣는 것도 가능하다.

//    @ManyToOne
//    private AddressNick addressNick; // 임베디드 타입에 Entity를 넣어 연관관계 정의도 가능하다. (가능한데 임베디드 타입의 목적을 생각하면, 이건 잘 쓸일이 없을 것 같다.)


    public AddressC() {

    }
    public AddressC(String city, String street, String zipcode) {
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
    public boolean equals(Object o) { // 자동완성으로 만든 equals 메서드
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressC addressC = (AddressC) o;
        return Objects.equals(city, addressC.city) && Objects.equals(street, addressC.street) && Objects.equals(zipcode, addressC.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}
