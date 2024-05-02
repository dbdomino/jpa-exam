drop table if exists item CASCADE;
create table item
(
    id        bigint generated by default as identity,
    item_name varchar(10),
    price     integer,
    quantity  integer,
    primary key (id)
);

13:51:53.647 [main] INFO  o.h.e.t.j.p.i.JtaPlatformInitiator --HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
13:51:53.681 [main] DEBUG org.hibernate.SQL --
create table public.item (
                             item_id bigint not null,
                             name varchar(255),
                             price integer not null,
                             stock_quantity integer not null,
                             primary key (item_id)
)
    13:51:53.690 [main] DEBUG org.hibernate.SQL --
create table public.mbr (
                            id bigint not null,
                            username varchar(255),
                            primary key (id)
)
    13:51:53.692 [main] DEBUG org.hibernate.SQL --
create table public.mbr2 (
                             id bigint not null,
                             username varchar(255),
                             primary key (id)
)
    13:51:53.695 [main] DEBUG org.hibernate.SQL --
create table public.member (
                               member_id bigint not null,
                               city varchar(255),
                               name varchar(255),
                               street varchar(255),
                               zipcode varchar(255),
                               primary key (member_id)
)
    13:51:53.699 [main] DEBUG org.hibernate.SQL --
create table public.order_item (
                                   order_item_id bigint not null,
                                   count integer not null,
                                   item_id bigint,
                                   order_id bigint,
                                   order_price integer not null,
                                   primary key (order_item_id)
)
    13:51:53.701 [main] DEBUG org.hibernate.SQL --
create table public.orders (
                               order_id bigint not null,
                               member_id bigint,
                               order_date timestamp(6),
                               status varchar(255) check (status in ('ORDER','CANCEL')),
                               primary key (order_id)
)
    13:51:53.707 [main] DEBUG org.hibernate.SQL --
create table public.smodey (
                               id bigint not null,
                               name varchar(255),
                               primary key (id)
)
    13:51:53.709 [main] DEBUG org.hibernate.SQL --
create table public.user (
                             id bigint not null,
                             age integer,
                             created_date timestamp(6),
                             description oid,
                             last_modified_date timestamp(6),
                             role_type varchar(255) check (role_type in ('ENOY1','ENOY2')),
                             name varchar(255),
                             primary key (id)
)
    13:51:53.712 [main] DEBUG org.hibernate.SQL --
create table public.usera (
                              id bigint not null,
                              name varchar(255) not null,
                              primary key (id)
)
    13:51:53.716 [main] DEBUG org.hibernate.SQL --
create sequence public.item_seq start with 1 increment by 50
    13:51:53.717 [main] DEBUG org.hibernate.SQL --
create sequence public.mbr2_seq start with 1 increment by 30
    13:51:53.719 [main] DEBUG org.hibernate.SQL --
create sequence public.mbr_seq start with 1 increment by 1
    13:51:53.720 [main] DEBUG org.hibernate.SQL --
create sequence public.member_seq_generator start with 1 increment by 50
    13:51:53.721 [main] DEBUG org.hibernate.SQL --
create sequence public.order_item_seq start with 1 increment by 50
    13:51:53.722 [main] DEBUG org.hibernate.SQL --
create sequence public.orders_seq start with 1 increment by 50