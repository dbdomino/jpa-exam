package com.minod.itemservice.domain;

import java.util.Objects;

/**
 * FAST: 빠른 배송
 * NORMAL: 일반 배송
 * SLOW: 느린 배송
 */
//@Data
//@AllArgsConstructor
public class DeliveryCode {

    private String code;
    private String displayName;

    public DeliveryCode() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryCode that = (DeliveryCode) o;
        return Objects.equals(getCode(), that.getCode()) && Objects.equals(getDisplayName(), that.getDisplayName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getDisplayName());
    }
}
