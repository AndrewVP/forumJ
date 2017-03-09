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

CREATE TABLE posts SELECT * FROM fd_post001;
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


insert into posts
  SELECT
      body.id                     AS id
    , headers.thread_id
    , headers.auth              AS auth
    , headers.fd_post_time      AS fd_post_time
    , body.fd_state
    , headers.tilte             AS tilte
    , headers.ip                AS ip
    , headers.domen             AS domen
    , headers.nred              AS nred
    , headers.fd_post_edit_time AS fd_post_edit_time
    , fd_posts.body             AS body
  FROM body
    LEFT JOIN headers ON headers.id = body.id LEFT JOIN fd_posts ON fd_posts.id = body.id;

CREATE TABLE posts
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  thread BIGINT(20) NOT NULL,
  author BIGINT(20) NOT NULL,
  created BIGINT(20) NOT NULL,
  state TINYINT(4),
  title CHAR(255) NOT NULL,
  ip CHAR(15),
  domen CHAR(100),
  edited_times TINYINT(4) DEFAULT '0',
  edited BIGINT(20),
  post TEXT NOT NULL
);
CREATE UNIQUE INDEX posts_id_uindex ON posts (id);