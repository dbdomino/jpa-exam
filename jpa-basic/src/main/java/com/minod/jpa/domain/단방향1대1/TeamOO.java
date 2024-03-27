package com.minod.jpa.domain.단방향1대1;

import com.minod.jpa.domain.단방향1대N.MbrOne;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TeamOO {
    @Id
    @GeneratedValue
    @Column(name ="TEAM_ID")
    private Long id;
    private String name;

    @OneToMany
    @JoinColumn(name="TEAM_ID")
    List<MbrOne> members = new ArrayList<MbrOne>(); // ArrayList로 초기화 해두는게 관례임.

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
