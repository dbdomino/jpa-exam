package com.minod.jpa.domain;

import jakarta.persistence.*;
import lombok.ToString;

/** 자동으로 생성되는 테이블명은 mbr로 확인 됨.
 * create table public.mbr_persist (
 *         id bigint not null,
 *         name varchar(255),
 *         primary key (id)
 *     )
 */
@Entity
@ToString
public class MbrPersist {
    @Id @GeneratedValue
    private Long id;
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne  // JPA한테 연관 관계를 알려주기 위해서 어노테이션, 사용 누가 Many고 누가 One인지 구분해줘야 함. DB에서 사용되는 기준을 JPA에 알려주기위함.
    // DB에선 Member가 Many, Team이 One 이다. 그래서 @ManyToOne 을 사용함.
    @JoinColumn(name = "TEAM_ID") // 대상 엔티티의 컬럼 이름 알려줌.
    private Team team;

    public MbrPersist(){}
    public MbrPersist(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public MbrPersist(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
