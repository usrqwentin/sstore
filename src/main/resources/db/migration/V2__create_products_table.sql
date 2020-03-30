create type product_type as enum('qty', 'weighted');

create table products
(
	id bigserial not null,
	name varchar(255),
	ean int,
	image_url varchar(128),
	product_type product_type
);

create unique index products_id_uindex
	on products (id);

alter table products
	add constraint products_pk
		primary key (id);

