package com.minod.jpa.domain.embededtype;

import jakarta.persistence.*;

@Entity
public class MemberEmbeded { // BaseEmbeded를 상속해서 컬럼에 속성 추가하는 것과는 다른 내용임.

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // 이런 아래의 속성들의 묶음을 객체로만들어 DB 테이블에 매핑해서 쓰려고한다. (객체지향적으로 쓰려고 이러는거)
    // PeriodC Type(실제로 클래스이지만 Type이라고 명명하는게 혼동이 방지된다.)
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
    @Embedded  // 핵심, 객체 참조로 Entity 속성을 DB 테이블에 매핑시키기 위해 애너테이션 사용해야함. 안쓰면 에러남.
    private PeriodC period;

    // AddressC Type
//    String city;
//    String street;
//    String zipcode;
    @Embedded
    private AddressC address;

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

    public AddressC getAddress() {
        return address;
    }

    public void setAddress(AddressC address) {
        this.address = address;
    }
}
