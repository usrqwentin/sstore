create table shops
(
    id bigserial not null,
    name varchar,
    status varchar,
    address varchar,
    owner_id bigint
);

create unique index shops_id_uindex
    on shops (id);

alter table shops
    add constraint table_name_pk
        primary key (id);

create table user_shop
(
    id bigserial,
    user_id bigint,
    shop_id bigint
);

create unique index user_shop_id_uindex
    on user_shop (id);

alter table user_shop
    add constraint user_shop_pk
        primary key (id);