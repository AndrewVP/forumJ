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
ALTER TABLE posts ADD PRIMARY KEY(`id`);
insert into posts select * from fd_post002;
insert into posts select * from fd_post003;
insert into posts select * from fd_post004;
insert into posts select * from fd_post005;
insert into posts select * from fd_post006;
insert into posts select * from fd_post007;
insert into posts select * from fd_post008;
insert into posts select * from fd_post009;
insert into posts select * from fd_post010;
insert into posts select * from fd_post011;
insert into posts select * from fd_post012;

update body set table_head = 'headers';
update body set table_post = 'posts';
