create table if not exists users (
  id int auto_increment primary key,
  first_name varchar(255) not null,
  last_name varchar(255) not null,
  mail varchar(255) not null
);

insert into users(first_name, last_name, mail) values
    ("example", "example", "example@mail.ru"),
    ("lex", "lex", "lex@mail.ru"),
    ("afk", "afk", "afk@mail.ru");