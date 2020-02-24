create table public.profiles (
  id       bigint not null constraint profiles_id_pk primary key,
  login    varchar(64),
  password varchar(64),
  fullname varchar(64),
  email    varchar(24),
  phone    varchar(24),
  icon_uri varchar(128),
  status   varchar(24),
  role     varchar(24)
);

create unique index profiles_id_uindex on profiles (id);