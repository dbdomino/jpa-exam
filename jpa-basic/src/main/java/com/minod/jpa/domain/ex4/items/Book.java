package com.minod.jpa.domain.ex4.items;

//@Entity
//@DiscriminatorValue("B") // JPA 에서 제공하는 DTYPE 이름을 엔티티 이름 대신 수동으로 지정이 가능하다.
public class Book extends Item {
    // Item엔티티를 상속해서, BookEntity를 구현했다.
    /* create 명령을 보면 다음처럼 나온다. 상속해서 그런지 alter로 외래키 설정 제약조건까지 추가된다.
     create table public.book (
        author varchar(255),
        isbn varchar(255),
        id bigint not null,
        primary key (id)
    )
    alter table if exists public.book
       add constraint FKqk00l5u7w76kq5n45m9h5t5rj
       foreign key (id)
       references public.item
     */
    private String author;
    private String isbn;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "id : "+this.getId()+", name: "+this.getName()+", Price: "+this.getPrice()+", author: "+author+", isbn: "+isbn;
    }
}
