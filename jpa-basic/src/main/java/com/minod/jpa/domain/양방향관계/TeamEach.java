package com.minod.jpa.domain.양방향관계;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

// 객체를 테이블에 맞춰 모델링
@Entity
@ToString
public class TeamEach {

    @Id @GeneratedValue
    @Column(name ="TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "teamEach")
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
