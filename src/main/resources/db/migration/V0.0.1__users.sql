create table if not exists users (
  id int auto_increment primary key,
  first_name varchar(255) not null,
  last_name varchar(255) not null,
  mail varchar(255) not null
);