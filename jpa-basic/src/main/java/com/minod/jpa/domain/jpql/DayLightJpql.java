package com.minod.jpa.domain.jpql;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("DLJ")
public class DayLightJpql extends DayJpql{

    private String light;

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    @Override
    public String toString() {
        return "MemberJpql{" +
                "id=" + super.getId() +
                ", username='" + super.getUsername() + '\'' +
                ", age=" + super.getAge() +
                ", day=" + super.getDay() +
                ", light=" + this.getLight() +
                '}';
    }
}
