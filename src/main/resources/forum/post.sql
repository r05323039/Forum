# table category
DROP TABLE IF EXISTS category;
create table category
(
    id       int auto_increment
        primary key,
    category varchar(50)  not null,
    img      varchar(200) not null,
    level    int          not null,
    constraint category
        unique (category),
    constraint checklevel
        check (`level` in (0, 1, 2))
);
# insert category
insert into category (category, img, level)
VALUES ('育嬰', 'fa-solid fa-baby', 0),
       ('出遊', 'fa-solid fa-plane-departure', 0),
       ('花費', 'fa-solid fa-comments-dollar', 0),
       ('寵物', 'fa-solid fa-dog', 0),
       ('健康', 'fa-solid fa-briefcase-medical', 0),
       ('財經', 'fa-solid fa-money-bill-trend-up', 1),
       ('房地產', 'fa-solid fa-house-building', 1),
       ('好康福利','fa-solid fa-venus-mars',1),
       ('會員專區', 'fa-solid fa-money-check-dollar', 2);

# table post
DROP TABLE IF EXISTS post;
create table post
(
    post_id  int auto_increment
        primary key,
    user_id  int                                 not null,
    category varchar(50)                         not null,
    topic    varchar(50)                         not null,
    content  text                                not null,
    timing   timestamp default CURRENT_TIMESTAMP null,
    constraint post_category_category_fk
        foreign key (category) references elitebaby.category (category),
    constraint post_member_id_fk
        foreign key (user_id) references elitebaby.member (USER_ID)
);
# varchar被外鍵，要先index
create index post_category_index
    on elitebaby.post (category);
# insert post
insert into post (user_id, category, topic, content)
VALUES (1, '健康', '多吃蔬菜有益健康', ' '),
       (2, '出遊', '蜜月旅行攻略', ' '),
       (3, '寵物', '學步期小孩和寵物！安全共處必知要點', ' '),
       (4, '健康', '健康好吃的兒童食品', ' '),
       (5, '出遊', '免費消耗小孩電力哪裡去？', ' '),
       (3, '育嬰', '如何協助孩子長得又高又健康', ' '),
       (1, '花費', '請問大家的育嬰開銷?', ' '),
       (4, '財經', '育嬰補貼申請懶人包', ' '),
       (5, '健康', '小孩逃避青菜怎麼辦?', ' '),
       (1, '出遊', '今年夏季旅遊推薦', ' '),
       (2, '寵物', '談談狗狗與新生兒的相處之道', ' '),
       (5, '健康', '嬰兒食品大開箱', ' '),
       (3, '出遊', '帶小孩旅行很辛苦嗎?', ' '),
       (1, '育嬰', '孩子的學習不能等?', ' '),
       (5, '花費', '哪牌的尿布CP值最高?', ' '),
       (4, '財經', '小孩長得很醜', ' '),
       (1, '房地產', '高房價造成生育率創新低?', ' '),
       (2, '房地產', '央行預測下半年減緩升息', ' '),
       (2, '會員專區', 'elitebaby會員獨享', ' ');

# content 填充
update post
set content = '123'
where 1 = 1;
update post
set content ='Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.長用那其張傷作良落希地國天高？起們表皮。期間同院時發教你；食總寶賣教件哥商，研問臺世：稱布濟費天樹明大著、就管圖了通好務以麼己些在說花帶子德朋求中，化果一產內男致？出寫提們卻一！中照食。國華星後然，大同省，大應事事故明機麼好微熱有。一手也在……見在黃今國的老開首先趣夜、角似馬好電之的後生寫哥統生個著究任夫從不老外人條個試政寫，的音童外要最去。你答查；不我事不的起會經子面式小在民是商這見下，體人黨發的屋在造我的費創花種爸酒雲候星。心不賽的，社跑其和深存看兒局電公種深獨戰息行機裡型詩管關富黃時，我進加能向除投在數要更代花在個何面頭人千大新幾第！何在方一中意裡言生找仍這重行於教不人建些大能。的我設全神事、式體問般新民謝最須雄減生望看歡建他太上界兒展國布士。'
where 1 = 1;

# ---------post_images----------
create table post_images
(
    img_id  int auto_increment
        primary key,
    post_id int      not null,
    img     longblob not null,
    constraint postimg_post_post_id_fk
        foreign key (post_id) references post (post_id)
);

# ----------post_like----------
create table post_like
(
    like_id int auto_increment
        primary key,
    post_id int not null,
    user_id int not null,
    constraint post_like_pk
        unique (post_id, user_id),
    constraint post_like_member_USER_ID_fk
        foreign key (user_id) references member (USER_ID),
    constraint postlike_post_post_id_fk
        foreign key (post_id) references post (post_id)
);

# ----------post_reports----------
create table post_reports
(
    reports_id int auto_increment
        primary key,
    user_id    int                                 not null,
    post_id    int                                 not null,
    reason     text                                not null,
    timing     timestamp default CURRENT_TIMESTAMP null,
    constraint reports_pk
        unique (user_id, post_id),
    constraint reports_member_USER_ID_fk
        foreign key (user_id) references elitebaby.member (USER_ID),
    constraint reports_post_post_id_fk
        foreign key (post_id) references elitebaby.post (post_id)
);

