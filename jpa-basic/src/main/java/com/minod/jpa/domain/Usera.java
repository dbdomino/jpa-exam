package com.minod.jpa.domain;

import jakarta.persistence.*;

//@Entity
@SequenceGenerator(
        name = "USERA_SEQ_GENERATOR",
        sequenceName = "USERA_SEQ",
        initialValue=1, allocationSize=1 )
public class Usera {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;


}
