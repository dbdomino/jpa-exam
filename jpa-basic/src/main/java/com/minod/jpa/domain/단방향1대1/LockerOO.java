package com.minod.jpa.domain.단방향1대1;

import jakarta.persistence.*;

//@Entity
public class LockerOO {
    @Id @GeneratedValue
    @Column(name ="LOCKER_ID")
    private Long id;
    private String name;

    @OneToOne(mappedBy = "locker")
    private MbrOO member;
}
