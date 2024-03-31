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

    @ManyToOne
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
}
