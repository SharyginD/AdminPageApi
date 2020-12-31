create table if not exists admins (
  id int auto_increment primary key,
  login varchar(255) not null,
  password varchar(255) not null
);