package com.minod.jpa.domain.jpql;

import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

//@Entity
public class TeamBatchSizeJpql {
    @Id    @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;
    private String name;

    // 양방향 만들기위해 추가
    @BatchSize(size=100) // 아래 매핑이 되어있다고 해도, 연관되는 PK 기준 하나마다 select 하나씩 날리는 1+N이 발생하게 되는데, 그걸 완화하기 위해 씀.
    // 한번에 조회할 때 teamId 하나씩기준으로 조회되는게 아니라, TeamId 조회할 개수 기준으로 select를 날릴 수 있게 해줌.
    // fetch 조인처럼 한번에 조회하는 건 아니지만, 그렇다고해서 TeamId개수 N개 가 아닌 위 지정된 100개 단위 기준만큼만 조회할 수 있도록 해주는 어노테이션이라 효율적임.
    // 구체적으로 select 한번에 teamId 1, 2, 3 이 같이 조회되어 team1일경우 team2일 경우 ... 각각나눠 하이버네이트가 매핑해준다. select 횟수만 줄어들게하는 용으로 쓰며 그것만으로도 성능적으로 유리해진다.
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

//    @Override
//    public String toString() {
//        return "TeamJpql{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
