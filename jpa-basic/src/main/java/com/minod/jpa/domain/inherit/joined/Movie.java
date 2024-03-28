package com.minod.jpa.domain.inherit.joined;

import jakarta.persistence.DiscriminatorValue;

//@Entity
@DiscriminatorValue("M") // JPA 에서 제공하는 DTYPE 이름을 엔티티 이름 대신 수동으로 지정이 가능하다.
public class Movie extends Item{
    private String director;
    private String actor;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "id : "+this.getId()+", name: "+this.getName()+", Price: "+this.getPrice()+", director: "+director+", actor: "+actor;
    }
}
