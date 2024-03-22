package com.minod.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table   // 테이블 id 원하는데로 지정가능
/**   기본적으로 클래스 이름대로 테이블 이름이 기본값으로 매핑되며, 소문자로 테이블 만들어짐.
 * create table public.smodey (
 *         id bigint not null,
 *         name varchar(255),
 *         primary key (id)
 *     )
 */
public class Smodey {
    @Id
    private Long id;
    private String name;

    public Smodey(){}

    public Smodey(Long id, String name) {
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
