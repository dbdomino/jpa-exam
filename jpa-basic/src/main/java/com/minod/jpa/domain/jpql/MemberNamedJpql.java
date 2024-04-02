package com.minod.jpa.domain.jpql;

import com.minod.jpa.domain.embededtype.BaseEmbeded;
import jakarta.persistence.*;

@Entity
@NamedQuery( // name ="StaticQuery"   이런식으로 그냥 써도 되지만, 엔티티명.쿼리명 으로 쓰는게 관례 라고 함.
        name = "MemberNamedJpql.findByUsername",
        query = "select m from MemberNamedJpql m where m.username = :username" // 쿼리 이상할 경우컴파일에러로 스프링 실행안됨. (이게 장점이라고 함)
)
@NamedQuery( // name ="StaticQuery"   이런식으로 그냥 써도 되지만, 엔티티명.쿼리명 으로 쓰는게 관례 라고 함.
        name = "MemberNamedJpql.findByUsername2",
        query = "select m from MemberNamedJpql m where m.username = :username" // 쿼리 이상할 경우컴파일에러로 스프링 실행안됨. (이게 장점이라고 함)
)
public class MemberNamedJpql extends BaseEmbeded {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계일 경우 항상 지연로딩되도록
//    @ManyToOne // 다대일 관계일 경우 항상 지연로딩되도록
    @JoinColumn(name="TEAM_ID") // 이게 외래키로 있어서 설정해줌.
    private TeamJpql teamJpql;

    public MemberNamedJpql(){}
    public MemberNamedJpql(String username) {
        this.username = username;
    }
    public MemberNamedJpql(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public MemberNamedJpql(String username, Integer age) {
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


}
