package com.minod.jpa.domain.jpql;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TeamJpql {
    @Id    @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;
    private String name;

    // 양방향 만들기위해 추가
    @OneToMany(mappedBy = "teamJpql")
    private List<MemberJpql> memberJpqlList = new ArrayList<>(); // 메모리가 사용되더라도 이렇게 new ArrayList<>(); 하는걸 권장한다고 함.

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

    public List<MemberJpql> getMemberJpqlList() {
        return memberJpqlList;
    }

    public void setMemberJpqlList(List<MemberJpql> memberJpqlList) {
        this.memberJpqlList = memberJpqlList;
    }
}
