create table public.users (
  id       bigint not null constraint users_id_pk primary key,
  username    varchar(64),
  password varchar(64),
  fullname varchar(64),
  email    varchar(24),
  phone    varchar(24),
  icon_uri varchar(128),
  status   varchar(24),
  role     varchar(24)
);

create unique index users_id_uindex on users (id);

create sequence public.hibernate_sequence start 1;