package com.minod.jpa.domain.jpql;

import com.minod.jpa.domain.embededtype.BaseEmbeded;
import jakarta.persistence.*;

@Entity
@SequenceGenerator(
        name = "MBR_SEQ_GENERATOR",
        sequenceName = "MBR_SEQ",
        initialValue=1, allocationSize=1 )
public class MemberJpql extends BaseEmbeded {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MBR_SEQ_GENERATOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MBR_SEQ_GENERATOR")
    private Long id;
    private String username;
    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계일 경우 항상 지연로딩되도록
    @JoinColumn(name="TEAM_ID") // 이게 외래키로 있어서 설정해줌.
    private TeamJpql teamJpql;

    public MemberJpql(){}
    public MemberJpql(String username) {
        this.username = username;
    }
    public MemberJpql(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public MemberJpql(String username, Integer age) {
        this.username = username;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public TeamJpql getTeamJpql() {
        return teamJpql;
    }

    public void setTeamJpql(TeamJpql teamJpql) {
        this.teamJpql = teamJpql;
    }

    // 연관관계 편의 메소드
    // 양쪽 객체에 값 주입시켜줌
    public void changeTeam(TeamJpql teamJpql) {
        this.teamJpql = teamJpql;
        teamJpql.getMemberJpqlList().add(this);
    }

    @Override
    public String toString() {
        return "MemberJpql{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", teamid=" + teamJpql.getId() +
//                ", teamName=" + teamJpql.getName() +
                '}';
    }
}
