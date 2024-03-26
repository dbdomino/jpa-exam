package com.minod.jpa.domain.양방향관계;

import jakarta.persistence.*;

/** 자동으로 생성되는 테이블명은 mbr로 확인 됨.
 * create table public.mbr_persist (
 *         id bigint not null,
 *         name varchar(255),
 *         primary key (id)
 *     )
 */
@Entity
public class MbrEach {
    @Id @GeneratedValue
    private Long id;
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne  // JPA한테 연관 관계를 알려주기 위해서 어노테이션, 사용 누가 Many고 누가 One인지 구분해줘야 함. DB에서 사용되는 기준을 JPA에 알려주기위함.
    // DB에선 Member가 Many, Team이 One 이다. 그래서 @ManyToOne 을 사용함.
    @JoinColumn(name = "TEAM_ID") // 대상 엔티티의 컬럼 이름 알려줌.
    private TeamEach teamEach; // 기본적으로 eitity 객체가 들어가면 어노테이션으로 참조관계 설정 반드시 해줘야 하는구나.

    public MbrEach(){}
    public MbrEach(String username) {
        this.username = username;
    }

    public MbrEach(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public TeamEach getTeamEach() {
        return teamEach;
    }

    public void setTeamEach(TeamEach teamEach) {
        this.teamEach = teamEach;
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
