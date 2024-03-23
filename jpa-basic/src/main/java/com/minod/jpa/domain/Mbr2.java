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
@Entity
//@Table(name = "MBR")   // 테이블 id 원하는데로 지정가능,
@SequenceGenerator(
        name = "MBR2_SEQ_GENERATOR",
        sequenceName = "MBR2_SEQ",
        initialValue=1, allocationSize=30 )
@ToString
public class Mbr2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MBR2_SEQ_GENERATOR")
    private Long id;
    private String username;

    public Mbr2(){}
    public Mbr2(String username) {
        this.username = username;
    }
    public Mbr2(Long id, String username) {
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
