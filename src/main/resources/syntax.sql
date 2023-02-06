
-- 更新時間
timing timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
-- # select popular
select p.*, m.user_name, count(l.like_id) as plike
from post p
         left join postlike l on p.post_id = l.post_id
         join member m on m.USER_ID = p.user_id
group by p.post_id
order by plike desc;
-- # select newest post
select p.*, m.user_name, count(l.like_id) as plike
from post p
         left join postlike l on p.post_id = l.post_id
         join member m on m.USER_ID = p.user_id
group by p.post_id
order by p.post_id desc ;