package com.minod.jpa.domain.embededtype;

import jakarta.persistence.*;

@Entity
public class MemberReuseEmbeded { // BaseEmbeded를 상속해서 컬럼에 속성 추가하는 것과는 다른 내용임.

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Embedded
    private PeriodC period;

    @Embedded
    private AddressC addressA;

    @Embedded // 단순히 이렇게만 쓰면 AddressC 중복으로 쓰여서 에러발생
    @AttributeOverrides({  // 이걸로 AddressC 를 중복으로 사용가능, 일일이 하나씩 재정의해줘야함. 컬럼 명 속성을 재정의
            @AttributeOverride(name="city", column=@Column(name = "OP_CITY")),
            @AttributeOverride(name="street", column=@Column(name = "OP_STREET")),
            @AttributeOverride(name="zipcode", column=@Column(name = "OP_ZIPCODE")),
    })
    private AddressC addressB;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PeriodC getPeriod() {
        return period;
    }

    public void setPeriod(PeriodC period) {
        this.period = period;
    }

    public AddressC getAddressA() {
        return addressA;
    }

    public void setAddressA(AddressC addressA) {
        this.addressA = addressA;
    }

    public AddressC getAddressB() {
        return addressB;
    }

    public void setAddressB(AddressC addressB) {
        this.addressB = addressB;
    }
}
