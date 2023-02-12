----------msg----------
DROP TABLE IF EXISTS msg;
create table msg
(
    msg_id  int auto_increment
        primary key,
    user_id int                                 not null,
    post_id int                                 not null,
    content text                                not null,
    timing  timestamp default CURRENT_TIMESTAMP not null,
    constraint msg_member_USER_ID_fk
        foreign key (user_id) references elitebaby.member (USER_ID),
    constraint msg_post_post_id_fk
        foreign key (post_id) references elitebaby.post (post_id)
);

----------msg_images----------
create table msg_images
(
    img_id int auto_increment
        primary key,
    msg_id int      not null,
    img    longblob not null,
    constraint msg_images_msg_msg_id_fk
        foreign key (msg_id) references msg (msg_id)
);

----------msg_like----------
create table msg_like
(
    like_id int auto_increment
        primary key,
    msg_id int not null,
    user_id int not null,
    constraint msg_like_pk
        unique (msg_id, user_id),
    constraint msg_like_member_USER_ID_fk
        foreign key (user_id) references member (USER_ID),
    constraint msglike_msg_msg_id_fk
        foreign key (msg_id) references msg (msg_id)
);

----------msg_reports----------
create table msg_reports
(
    reports_id int auto_increment
        primary key,
    user_id    int                                 not null,
    msg_id    int                                 not null,
    reason     text                                not null,
    timing     timestamp default CURRENT_TIMESTAMP null,
    constraint reports_pk
        unique (user_id, msg_id),
    constraint msgreports_member_USER_ID_fk
        foreign key (user_id) references elitebaby.member (USER_ID),
    constraint reports_msg_msg_id_fk
        foreign key (msg_id) references elitebaby.msg (msg_id)
);