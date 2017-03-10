CREATE TABLE headers SELECT * FROM fd_head001;
ALTER TABLE `headers` ADD PRIMARY KEY(`id`);
ALTER TABLE `headers` ADD INDEX `auth` (`auth` ASC);
ALTER TABLE `headers` ADD INDEX `thread_id` (`thread_id` ASC);
ALTER TABLE `headers` ADD INDEX `id1` (`fd_post_time` ASC, `thread_id` ASC, `auth` ASC, `id` ASC);

insert into headers select * from fd_head002;
insert into headers select * from fd_head003;
insert into headers select * from fd_head004;
insert into headers select * from fd_head005;
insert into headers select * from fd_head006;
insert into headers select * from fd_head007;
insert into headers select * from fd_head008;
insert into headers select * from fd_head009;
insert into headers select * from fd_head010;
insert into headers select * from fd_head011;
insert into headers select * from fd_head012;

CREATE TABLE fd_posts SELECT * FROM fd_post001;
ALTER TABLE fd_posts ADD PRIMARY KEY(`id`);
insert into fd_posts select * from fd_post002;
insert into fd_posts select * from fd_post003;
insert into fd_posts select * from fd_post004;
insert into fd_posts select * from fd_post005;
insert into fd_posts select * from fd_post006;
insert into fd_posts select * from fd_post007;
insert into fd_posts select * from fd_post008;
insert into fd_posts select * from fd_post009;
insert into fd_posts select * from fd_post010;
insert into fd_posts select * from fd_post011;
insert into fd_posts select * from fd_post012;

update body set table_head = 'headers';
update body set table_post = 'fd_posts';
update fd_setup set current_body_head_table = 'headers', curret_body_table = 'fd_posts';

drop table fd_head001;
drop table fd_head002;
drop table fd_head003;
drop table fd_head004;
drop table fd_head005;
drop table fd_head006;
drop table fd_head007;
drop table fd_head008;
drop table fd_head009;
drop table fd_head010;
drop table fd_head011;
drop table fd_head012;

drop table fd_post001;
drop table fd_post002;
drop table fd_post003;
drop table fd_post004;
drop table fd_post005;
drop table fd_post006;
drop table fd_post007;
drop table fd_post008;
drop table fd_post009;
drop table fd_post010;
drop table fd_post011;
drop table fd_post012;



CREATE TABLE posts
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  type INT DEFAULT '0',
  thread BIGINT NOT NULL,
  author BIGINT NOT NULL,
  created BIGINT NOT NULL,
  state TINYINT,
  reply_to BIGINT DEFAULT '0',
  title CHAR(255) NOT NULL,
  ip CHAR(15),
  domen CHAR(100),
  edited_times TINYINT DEFAULT '0',
  edited BIGINT,
  post TEXT NOT NULL
);
CREATE UNIQUE INDEX posts_id_uindex ON posts (id);

insert into posts
  SELECT
    body.id
    , '0'
    , headers.thread_id
    , headers.auth
    , headers.fd_post_time
    , body.fd_state
    , '0'
    , headers.tilte
    , headers.ip
    , headers.domen
    , headers.nred
    , headers.fd_post_edit_time
    , fd_posts.body
  FROM body
    LEFT JOIN headers ON headers.id = body.id LEFT JOIN fd_posts ON fd_posts.id = body.id;
