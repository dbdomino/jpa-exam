package com.minod.jpa.domain;

import jakarta.persistence.*;
import lombok.ToString;

/** 자동으로 생성되는 테이블명은 mbr로 확인 됨.
 * create table public.mbr (
 *         id bigint not null,
 *         name varchar(255),
 *         primary key (id)
 *     )
 */
//@Entity
@Table(name = "MBR3")   // 테이블 id 원하는데로 지정가능,
@ToString
public class MemberSequenceGenerator {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MBR_SEQ_GENERATOR") // SEQUENCE DB의 sequence 객체를 이용해 유일한 값을 순서대로 생성한다.
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY 기본키 생성을 DB에게 위임한다. (MySQL의 경우는 AUTO INCREMENT)
//    @GeneratedValue(strategy = GenerationType.TABLE) // 시퀀스 테이블을 만들어서 데이터베이스 시퀀스를 흉내낼 Id를 할당한다. 즉 특정 DB 벤더에 의존적이지 않으나, 성능이 좋지않음.
    private Long id;
    private String username;

    public MemberSequenceGenerator(){}
    public MemberSequenceGenerator(String username) {
        this.username = username;
    }
    public MemberSequenceGenerator(Long id, String username) {
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
