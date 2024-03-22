package com.minod.jpa.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
/**
 * create table public.user (
 *         id bigint not null,
 *         age integer,
 *         created_date timestamp(6),
 *         description oid,
 *         last_modified_date timestamp(6),
 *         role_type varchar(255) check (role_type in ('ORDINAL','STRING')),
 *         name varchar(255),
 *         primary key (id)
 *     )
 */
public class User {
    @Id
    private Long id;
    @Column(name = "name")
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING) // Enum으로된 건 왠만하면 String으로 지정해주는게 좋다.
    private CustumEnumType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob
    private String description;

//    LocalDateTime.now() 자바 8부터, 시분초 다나옴.
 /*   System.out.println(LocalDate.now());
    System.out.println(LocalTime.now());
    System.out.println(LocalDateTime.now());*/


}
