package com.minod.jpa.domain.inherit.joined;

//@Entity
//@DiscriminatorValue("A") // JPA 에서 제공하는 DTYPE 이름을 엔티티 이름 대신 수동으로 지정이 가능하다.
public class Album extends Item{
    private String artist;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
