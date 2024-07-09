package com.minod.shop.web.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm { // 폼객체, 나중에 DTO와 역할이 같음... DTO로 대체 가능
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;
}
