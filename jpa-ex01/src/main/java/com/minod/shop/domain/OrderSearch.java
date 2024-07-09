package com.minod.shop.domain;

import com.minod.shop.domain.status.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus; // 주문상태 [ORDER, CANCEL]
}
