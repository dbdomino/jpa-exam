package com.minod.jpa.domain.jpql;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class DayJpql {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING) // 기본이 EnumType.ORDINAL 숫자이다. ORDINAL로 잡히면 smallint 타입으로 db에 저장되었다.
    private Day day;
    private Boolean booltype;

    public DayJpql(){}
    public DayJpql(String username) {
        this.username = username;
    }
    public DayJpql(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Boolean getBooltype() {
        return booltype;
    }

    public void setBooltype(Boolean booltype) {
        this.booltype = booltype;
    }

    public DayJpql(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
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

    @Override
    public String toString() {
        return "MemberJpql{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", day=" + day +
//                ", teamName=" + teamJpql.getName() +
                '}';
    }
}
