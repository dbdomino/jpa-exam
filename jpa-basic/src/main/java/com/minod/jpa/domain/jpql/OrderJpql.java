package com.minod.jpa.domain.jpql;

import jakarta.persistence.*;

@Entity
public class OrderJpql {
    @Id    @GeneratedValue
    private Long id;
    private Integer orderAmount;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private MemberJpql memberJpql;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private ProductJpql productJpql;

    @Embedded
    private AddressJ address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public MemberJpql getMemberJpql() {
        return memberJpql;
    }

    public void setMemberJpql(MemberJpql memberJpql) {
        this.memberJpql = memberJpql;
    }

    public ProductJpql getProductJpql() {
        return productJpql;
    }

    public void setProductJpql(ProductJpql productJpql) {
        this.productJpql = productJpql;
    }

    public AddressJ getAddress() {
        return address;
    }

    public void setAddress(AddressJ address) {
        this.address = address;
    }
}
