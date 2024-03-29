package com.minod.jpa.domain.lazyloading;


import jakarta.persistence.*;

// 양방향 관계는 단방향 관계들이 서로 마주보는 상태, 주인 참조 구분이 필요해짐, mappedBy 쓰는쪽은 주인이 아님.
@Entity
public class MbrLazyLoading {
    @Id @GeneratedValue
    private Long id;
    private String username;
//    private String teamId;

    // JPA한테 연관 관계를 알려주기 위해서 어노테이션 사용, 누가 Many고 누가 One인지 구분해줘야 함. DB에서 사용되는 기준을 JPA에 알려주기위함.
    // DB에선 Member가 Many, Team이 One 이다. 그래서 @ManyToOne 을 사용함.
    // 참조 관계에 (fetch 옵션을 줘서 참조 전략을 선택할 수 있다.  FetchType.EAGER FetchType.LAZY)
//    @ManyToOne
//    @ManyToOne(fetch=FetchType.LAZY)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID") // 대상 엔티티의 컬럼 이름 알려줌.
//    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false) // 참조한 teamEach에 뭔갈 넣는다고해서 추가로 team테이블에 insert나 update를 날리지 않는 설정.
    // 읽기전용으로만 참조를 할때 쓰는 설정, member를 통해서 TeamId 등록못하게 된다.
    private TeamLazyLoading team; // 기본적으로 entity 객체가 들어가면 어노테이션으로 참조관계 설정 반드시 해줘야 하는구나.

    public MbrLazyLoading(){}
    public MbrLazyLoading(String username) {
        this.username = username;
    }

    public MbrLazyLoading(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public TeamLazyLoading getTeamEach() {
        return team;
    }

    public void setTeamEach(TeamLazyLoading team) {
        this.team = team;
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

    public String getValue() {
//        return "{id : "+id+", username: "+username+", teamId: "+teamId+"}";
        return "{id : "+id+", username: "+username+", teamId: "+team.getId()+"}";
//        return "{id : "+id+", username: "+username+"}";
    }
}
