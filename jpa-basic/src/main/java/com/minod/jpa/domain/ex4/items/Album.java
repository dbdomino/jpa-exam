package com.minod.jpa.domain.ex4.items;

//@Entity
//@DiscriminatorValue("A") // JPA 에서 제공하는 DTYPE 이름을 엔티티 이름 대신 수동으로 지정이 가능하다.
public class Album extends Item {
    private String artist;
    private String etc;

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "id : "+this.getId()+", name: "+this.getName()+", Price: "+this.getPrice()+", artist: "+artist+", etc: "+etc;
    }

}
