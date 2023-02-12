----------collection_post----------
create table collection_post
(
    user_id int not null,
    post_id int not null,
    constraint post_collections_pk
        unique (post_id, user_id),
    constraint post_collections_member_USER_ID_fk
        foreign key (user_id) references elitebaby.member (USER_ID),
    constraint post_collections_post_post_id_fk
        foreign key (post_id) references elitebaby.post (post_id)
);

----------collection_category----------
DROP TABLE IF EXISTS collection_category;
create table collection_category
(
    id          int auto_increment
        primary key,
    user_id     int not null,
    category_id int not null,
    constraint collection_category_uk
        unique (user_id, category_id),
    constraint collection_category_category_id_fk
        foreign key (category_id) references elitebaby.category (id),
    constraint collection_category_member_USER_ID_fk
        foreign key (user_id) references elitebaby.member (USER_ID)
);
----------collection_user----------
create table collection_user
(
    user_id      int not null,
    coll_user_id int not null,
    primary key (user_id, coll_user_id),
    constraint collection_user_member_USER_ID_fk
        foreign key (user_id) references elitebaby.member (USER_ID),
    constraint collection_user_member_USER_ID_fk2
        foreign key (coll_user_id) references elitebaby.member (USER_ID)
);