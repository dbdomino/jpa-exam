package com.minod.jpa.domain.양방향관계;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// 객체를 테이블에 맞춰 모델링

@Entity
public class TeamEach {

    @Id @GeneratedValue
    @Column(name ="TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "teamEach")
//    @OneToMany  // 단순히 단방향 관계로 MbrEach 와 관계를 정의한다면 다음처럼 어노테이션 쓰면 될 것이다.
//    @JoinColumn(name = "TEAM_ID") // 대상 엔티티의 컬럼 이름 알려줌.
    List<MbrEach> MbrEach = new ArrayList<MbrEach>(); // ArrayList로 초기화 해두는게 관례임.

    public List<MbrEach> getMbrEach() {
        return MbrEach;
    }

    public void setMbrEach(List<MbrEach> mbrEach) {
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
