package com.minod.jpa.domain.ex;

import jakarta.persistence.*;
import lombok.ToString;

// 객체를 테이블에 맞춰 모델링
@Entity
@ToString
public class TeamMember {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;


    // 추가하기 연관관계, 객체의 참조, 이게 있으면 Team 객체를 외부에서 주입받아야 한다.
//    @Column(name = "TEAM_ID")  // 객체로 받기에 기존 TEAM_ID 와 매핑되는건 주석처리
//    private Long teamId;
    @ManyToOne
    @JoinColumn(name ="TEAM_ID")
    private Team team; // Team Table의 TEAM_ID 컬럼값이 해당 컬럼과 join된다.

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

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

//    public Long getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(Long teamId) {
//        this.teamId = teamId;
//    }
}
