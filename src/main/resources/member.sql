create table member
(
    USER_ID   int auto_increment
        primary key,
    user_name varchar(20) null
);

insert into member (user_name) values ('名1'),
                                      ('名2'),
                                      ('名3'),
                                      ('名4'),
                                      ('名5'),
                                      ('名6'),
                                      ('名7'),
                                      ('名8'),
                                      ('名9'),
                                      ('名10');
