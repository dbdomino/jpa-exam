01:20:22.481 [Test worker] DEBUG org.hibernate.SQL --
create table category (
                          category_id bigint not null,
                          name varchar(255),
                          parent_id bigint,
                          primary key (category_id)
)
    Hibernate:
create table category (
                          category_id bigint not null,
                          name varchar(255),
                          parent_id bigint,
                          primary key (category_id)
)
    01:20:22.497 [Test worker] DEBUG org.hibernate.SQL --
create table category_item (
                               category_id bigint not null,
                               item_id bigint not null
)
    Hibernate:
create table category_item (
                               category_id bigint not null,
                               item_id bigint not null
)
    01:20:22.499 [Test worker] DEBUG org.hibernate.SQL --
create table delivery (
                          id bigint not null,
                          city varchar(255),
                          street varchar(255),
                          zipcode varchar(255),
                          status varchar(255) check (status in ('READY','PROCESSING','COMPLETE')),
                          primary key (id)
)
    Hibernate:
create table delivery (
                          id bigint not null,
                          city varchar(255),
                          street varchar(255),
                          zipcode varchar(255),
                          status varchar(255) check (status in ('READY','PROCESSING','COMPLETE')),
                          primary key (id)
)
    01:20:22.506 [Test worker] DEBUG org.hibernate.SQL --
create table item (
                      dtype varchar(31) not null,
                      item_id bigint not null,
                      stock_quantity integer not null,
                      name varchar(255),
                      price integer not null,
                      artist varchar(255),
                      etc varchar(255),
                      author varchar(255),
                      ibsn varchar(255),
                      actor varchar(255),
                      director varchar(255),
                      primary key (item_id)
)
    Hibernate:
create table item (
                      dtype varchar(31) not null,
                      item_id bigint not null,
                      stock_quantity integer not null,
                      name varchar(255),
                      price integer not null,
                      artist varchar(255),
                      etc varchar(255),
                      author varchar(255),
                      ibsn varchar(255),
                      actor varchar(255),
                      director varchar(255),
                      primary key (item_id)
)
    01:20:22.510 [Test worker] DEBUG org.hibernate.SQL --
create table member (
                        member_id bigint not null,
                        city varchar(255),
                        street varchar(255),
                        zipcode varchar(255),
                        name varchar(255),
                        primary key (member_id)
)
    Hibernate:
create table member (
                        member_id bigint not null,
                        city varchar(255),
                        street varchar(255),
                        zipcode varchar(255),
                        name varchar(255),
                        primary key (member_id)
)
    01:20:22.515 [Test worker] DEBUG org.hibernate.SQL --
create table order_item (
                            order_item_id bigint not null,
                            count integer not null,
                            order_price integer not null,
                            item_id bigint,
                            order_id bigint,
                            primary key (order_item_id)
)
    Hibernate:
create table order_item (
                            order_item_id bigint not null,
                            count integer not null,
                            order_price integer not null,
                            item_id bigint,
                            order_id bigint,
                            primary key (order_item_id)
)
    01:20:22.519 [Test worker] DEBUG org.hibernate.SQL --
create table orders (
                        order_id bigint not null,
                        order_date timestamp(6),
                        status varchar(255) check (status in ('ORDER','CANCEL')),
                        delivery_id bigint,
                        member_id bigint,
                        primary key (order_id)
)
    Hibernate:
create table orders (
                        order_id bigint not null,
                        order_date timestamp(6),
                        status varchar(255) check (status in ('ORDER','CANCEL')),
                        delivery_id bigint,
                        member_id bigint,
                        primary key (order_id)
)
    01:20:22.522 [Test worker] DEBUG org.hibernate.SQL --
alter table if exists orders
drop constraint if exists UK_9ct0l8xfeaiqruabcqjh1neui
Hibernate:
alter table if exists orders
drop constraint if exists UK_9ct0l8xfeaiqruabcqjh1neui
01:20:22.525 [Test worker] WARN  o.h.e.jdbc.spi.SqlExceptionHelper --SQL Warning Code: 0, SQLState: 00000
01:20:22.526 [Test worker] WARN  o.h.e.jdbc.spi.SqlExceptionHelper --"uk_9ct0l8xfeaiqruabcqjh1neui" 제약 조건(해당 테이블: "orders")이 없음, 건너뜀
01:20:22.526 [Test worker] DEBUG org.hibernate.SQL --
alter table if exists orders
    add constraint UK_9ct0l8xfeaiqruabcqjh1neui unique (delivery_id)
    Hibernate:
alter table if exists orders
    add constraint UK_9ct0l8xfeaiqruabcqjh1neui unique (delivery_id)
    01:20:22.530 [Test worker] DEBUG org.hibernate.SQL --
create sequence category_seq start with 1 increment by 50
    Hibernate:
create sequence category_seq start with 1 increment by 50
    01:20:22.531 [Test worker] DEBUG org.hibernate.SQL --
create sequence delivery_seq start with 1 increment by 50
    Hibernate:
create sequence delivery_seq start with 1 increment by 50
    01:20:22.533 [Test worker] DEBUG org.hibernate.SQL --
create sequence item_seq start with 1 increment by 50
    Hibernate:
create sequence item_seq start with 1 increment by 50
    01:20:22.534 [Test worker] DEBUG org.hibernate.SQL --
create sequence member_seq start with 1 increment by 50
    Hibernate:
create sequence member_seq start with 1 increment by 50
    01:20:22.535 [Test worker] DEBUG org.hibernate.SQL --
create sequence order_item_seq start with 1 increment by 50
    Hibernate:
create sequence order_item_seq start with 1 increment by 50
    01:20:22.536 [Test worker] DEBUG org.hibernate.SQL --
create sequence orders_seq start with 1 increment by 50
    Hibernate:
create sequence orders_seq start with 1 increment by 50
    01:20:22.537 [Test worker] DEBUG org.hibernate.SQL --
alter table if exists category
    add constraint FK2y94svpmqttx80mshyny85wqr
    foreign key (parent_id)
    references category
    Hibernate:
alter table if exists category
    add constraint FK2y94svpmqttx80mshyny85wqr
    foreign key (parent_id)
    references category
    01:20:22.542 [Test worker] DEBUG org.hibernate.SQL --
alter table if exists category_item
    add constraint FKu8b4lwqutcdq3363gf6mlujq
    foreign key (item_id)
    references item
    Hibernate:
alter table if exists category_item
    add constraint FKu8b4lwqutcdq3363gf6mlujq
    foreign key (item_id)
    references item
    01:20:22.543 [Test worker] DEBUG org.hibernate.SQL --
alter table if exists category_item
    add constraint FKcq2n0opf5shyh84ex1fhukcbh
    foreign key (category_id)
    references category
    Hibernate:
alter table if exists category_item
    add constraint FKcq2n0opf5shyh84ex1fhukcbh
    foreign key (category_id)
    references category
    01:20:22.545 [Test worker] DEBUG org.hibernate.SQL --
alter table if exists order_item
    add constraint FKija6hjjiit8dprnmvtvgdp6ru
    foreign key (item_id)
    references item
    Hibernate:
alter table if exists order_item
    add constraint FKija6hjjiit8dprnmvtvgdp6ru
    foreign key (item_id)
    references item
    01:20:22.546 [Test worker] DEBUG org.hibernate.SQL --
alter table if exists order_item
    add constraint FKt4dc2r9nbvbujrljv3e23iibt
    foreign key (order_id)
    references orders
    Hibernate:
alter table if exists order_item
    add constraint FKt4dc2r9nbvbujrljv3e23iibt
    foreign key (order_id)
    references orders
    01:20:22.548 [Test worker] DEBUG org.hibernate.SQL --
alter table if exists orders
    add constraint FKtkrur7wg4d8ax0pwgo0vmy20c
    foreign key (delivery_id)
    references delivery
    Hibernate:
alter table if exists orders
    add constraint FKtkrur7wg4d8ax0pwgo0vmy20c
    foreign key (delivery_id)
    references delivery
    01:20:22.549 [Test worker] DEBUG org.hibernate.SQL --
alter table if exists orders
    add constraint FKpktxwhj3x9m4gth5ff6bkqgeb
    foreign key (member_id)
    references member
    Hibernate:
alter table if exists orders
    add constraint FKpktxwhj3x9m4gth5ff6bkqgeb
    foreign key (member_id)
    references member
    01:20:22.552 [Test worker] TRACE o.h.type.spi.TypeConfiguration$Scope --Handling #sessionFactoryCreated from [org.hibernate.internal.SessionFactoryImpl@7dda5b25] for TypeConfiguration
    01:20:22.552 [Test worker] INFO  o.s.o.j.LocalContainerEntityManagerFactoryBean --Initialized JPA EntityManagerFactory for persistence unit 'default'
    01:20:22.746 [Test worker] WARN  o.s.b.a.o.j.JpaBaseConfiguration$JpaWebConfiguration --spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
    01:20:22.772 [Test worker] INFO  o.s.b.a.w.s.WelcomePageHandlerMapping --Adding welcome page: class path resource [static/index.html]
    01:20:23.246 [Test worker] INFO  c.m.shop.tmp.MemberRepositoryExTest --Started MemberRepositoryExTest in 4.343 seconds (process running for 5.592)
    01:20:23.881 [Test worker] DEBUG org.hibernate.SQL --
select
    nextval('member_seq')
        Hibernate:
    select
        nextval('member_seq')
01:20:23.979 [Test worker] DEBUG org.hibernate.SQL --
select
    nextval('member_seq')
        Hibernate:
    select
        nextval('member_seq')
