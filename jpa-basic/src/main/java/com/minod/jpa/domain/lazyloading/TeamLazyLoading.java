package com.minod.jpa.domain.lazyloading;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// 객체를 테이블에 맞춰 모델링
// 양방향 관계는 단방향 관계들이 서로 마주보는 상태, 주인 참조 구분이 필요해짐, mappedBy 쓰는쪽은 주인이 아님.

@Entity
public class TeamLazyLoading {

    @Id @GeneratedValue
    @Column(name ="TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
//    @OneToMany  // 단순히 단방향 관계로 MbrEach 와 관계를 정의한다면 다음처럼 어노테이션 쓰면 될 것이다.
//    @JoinColumn(name = "TEAM_ID") // 대상 엔티티의 컬럼 이름 알려줌.
    List<MbrLazyLoading> MbrEach = new ArrayList<>(); // ArrayList로 초기화 해두는게 관례임.

    public List<MbrLazyLoading> getMbrEach() {
        return MbrEach;
    }

    public void setMbrEach(List<MbrLazyLoading> mbrEach) {
        MbrEach = mbrEach;
    }

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
}
