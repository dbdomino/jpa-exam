package com.minod.jpa.domain.jpql;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class AddressJ {
    String city;
    String street;
    String zipcode;

    public AddressJ() {
    }

    public AddressJ(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressJ addressJ = (AddressJ) o;
        return Objects.equals(getCity(), addressJ.getCity()) && Objects.equals(getStreet(), addressJ.getStreet()) && Objects.equals(getZipcode(), addressJ.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}
