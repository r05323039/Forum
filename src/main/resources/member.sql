DROP TABLE IF EXISTS member;
create table elitebaby.member
(
    USER_ID   int auto_increment
        primary key,
    user_name varchar(20) null,
    password  varchar(50) not null
);

insert into member (user_name, password)
values ('名1', 'password'),
       ('名2', 'password'),
       ('名3', 'password'),
       ('名4', 'password'),
       ('名5', 'password'),
       ('名6', 'password'),
       ('名7', 'password'),
       ('名8', 'password'),
       ('名9', 'password'),
       ('名10', 'password'),
       ('root', 'password');
