package com.minod.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MBR")   // 테이블 id 원하는데로 지정가능,
/** 자동으로 생성되는 테이블명은 mbr로 확인 됨.
 * create table public.mbr (
 *         id bigint not null,
 *         name varchar(255),
 *         primary key (id)
 *     )
 */
public class Member {
    @Id
    private Long id;
    private String name;

    public Member(){}

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
