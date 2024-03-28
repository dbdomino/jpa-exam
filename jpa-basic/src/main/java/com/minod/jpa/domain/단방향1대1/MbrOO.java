package com.minod.jpa.domain.단방향1대1;

import jakarta.persistence.*;

//@Entity
public class MbrOO {
    @Id
    @GeneratedValue
    private Long id;
    private String username;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private LockerOO locker;

    public MbrOO(){}
    public MbrOO(String username) {
        this.username = username;
    }

    public MbrOO(Long id, String username) {
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
