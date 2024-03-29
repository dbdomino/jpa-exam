package com.minod.jpa.domain.cascade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Child {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne // parent_id라는 컬럼이 자동으로 생성됨. 연관관계의 주인이라서 parent 테이블의 기본키를 외래키로 가져와버림, @JoinColumn 안써주니 이름이 자동으로 매핑되버림.
    private Parent parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
