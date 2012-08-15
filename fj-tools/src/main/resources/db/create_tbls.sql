-- --------------------------------------------------------

--
-- Структура таблицы `body`
--

CREATE TABLE IF NOT EXISTS `body` (
  `id` int(11) NOT NULL auto_increment,
  `head` int(11) default NULL,
  `fd_state` tinyint(4) NOT NULL default '0',
  `table_post` varchar(10) binary NOT NULL default 'body',
  `table_head` varchar(10) NOT NULL default '',
  PRIMARY KEY  (`id`),
  KEY `head` (`head`),
  KEY `id_count` (`id`,`head`),
  KEY `fd_state` (`fd_state`),
  KEY `count2` (`head`,`id`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_action`
--

CREATE TABLE IF NOT EXISTS `fd_action` (
  `id` int(11) NOT NULL auto_increment,
  `fd_ip` varchar(15) default NULL,
  `fd_subnet` varchar(15) default NULL,
  `fd_user` int(11) default NULL,
  `fd_time` datetime default NULL,
  `fd_page` varchar(200) default NULL,
  `fd_refer` varchar(200) default NULL,
  `fd_action` tinyint(4) default NULL,
  `fd_reefer` varchar(200) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fd_user` (`fd_user`),
  KEY `fd_subnet` (`fd_subnet`),
  KEY `fd_time` (`fd_time`),
  KEY `fd_ip` (`fd_ip`),
  KEY `fd_reefer` (`fd_reefer`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_head001`
--

CREATE TABLE IF NOT EXISTS `fd_head001` (
  `id` int(11) NOT NULL auto_increment,
  `auth` int(11) default NULL,
  `tilte` varchar(255) binary NOT NULL default '',
  `ip` varchar(15) NOT NULL default '',
  `domen` varchar(100) NOT NULL default '',
  `outd` varchar(100) NOT NULL default '',
  `nred` tinyint(4) NOT NULL default '0',
  `fd_post_time` int(11) NOT NULL default '0',
  `fd_post_edit_time` int(11) NOT NULL default '0',
  `id_post` int(11) NOT NULL default '0',
  `thread_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `auth` (`auth`),
  KEY `thread_id` (`thread_id`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_post001`
--

CREATE TABLE IF NOT EXISTS `fd_post001` (
  `id` int(11) NOT NULL auto_increment,
  `id_post` int(11) NOT NULL default '0',
  `body` text,
  PRIMARY KEY  (`id`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_post_user`
--

CREATE TABLE IF NOT EXISTS `fd_post_user` (
  `id` int(11) NOT NULL auto_increment,
  `iduser` int(11) NOT NULL default '0',
  `idpost` int(11) NOT NULL default '0',
  `fd_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `fd_open` tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `iduser` (`iduser`,`idpost`,`fd_date`,`fd_open`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_post_words`
--

CREATE TABLE IF NOT EXISTS `fd_post_words` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `fd_id_word_form` int(11) unsigned NOT NULL default '0',
  `fd_id_post` int(10) unsigned NOT NULL default '0',
  `fd_date` date NOT NULL default '0000-00-00',
  `fd_state` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_search_user`
--

CREATE TABLE IF NOT EXISTS `fd_search_user` (
  `id` int(11) NOT NULL auto_increment,
  `nick` char(50) NOT NULL default '',
  `fd_open` tinyint(4) NOT NULL default '0',
  `iduser` int(11) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `id` (`id`,`nick`,`fd_open`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_search_word_forms`
--

CREATE TABLE IF NOT EXISTS `fd_search_word_forms` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `fd_word` varchar(50) NOT NULL default '""',
  `fd_id_word` int(10) unsigned NOT NULL default '0',
  `fd_state` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `idx1` (`fd_word`,`id`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_search_wordforms`
--

CREATE TABLE IF NOT EXISTS `fd_search_wordforms` (
  `id` varchar(50) NOT NULL default '""',
  `fd_masterword` varchar(50) NOT NULL default '',
  `fd_masterword2` varchar(50) NOT NULL default '',
  `fd_masterword3` varchar(50) NOT NULL default '',
  `fd_state` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `masterword1` (`id`,`fd_masterword`),
  KEY `wordform1` (`fd_masterword`,`id`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_search_words`
--

CREATE TABLE IF NOT EXISTS `fd_search_words` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `fd_word` varchar(50) NOT NULL default '""',
  `fd_state` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `masterword1` (`fd_word`),
  KEY `wordform1` (`fd_word`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_searchstopwords`
--

CREATE TABLE IF NOT EXISTS `fd_searchstopwords` (
  `id` char(10) NOT NULL default '',
  `fd_state` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_searchwords`
--

CREATE TABLE IF NOT EXISTS `fd_searchwords` (
  `id` char(50) NOT NULL default '""',
  `fd_state` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_setup`
--

CREATE TABLE IF NOT EXISTS `fd_setup` (
  `curret_body_table` char(10) NOT NULL default '',
  `current_body_head_table` char(10) NOT NULL default ''
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_site`
--

CREATE TABLE IF NOT EXISTS `fd_site` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(200) NOT NULL default '',
  `head` int(11) NOT NULL default '0',
  `body` text NOT NULL,
  `auth` int(11) NOT NULL default '0',
  `time_post` datetime NOT NULL default '0000-00-00 00:00:00',
  `rubr` int(11) NOT NULL default '0',
  `time_site` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`),
  KEY `head` (`head`),
  KEY `auth` (`auth`),
  KEY `type` (`rubr`),
  KEY `time_site` (`time_site`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_subscribe`
--

CREATE TABLE IF NOT EXISTS `fd_subscribe` (
  `id` int(11) NOT NULL auto_increment,
  `user` int(11) NOT NULL default '0',
  `title` int(11) NOT NULL default '0',
  `d_start` datetime NOT NULL default '0000-00-00 00:00:00',
  `d_end` datetime NOT NULL default '2100-01-01 00:00:00',
  `kod` int(11) NOT NULL default '0',
  `type` tinyint(4) NOT NULL default '0',
  `act` tinyint(4) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `user` (`user`),
  KEY `title` (`title`),
  KEY `d_start` (`d_start`),
  KEY `d_end` (`d_end`),
  KEY `kod` (`kod`),
  KEY `type` (`type`),
  KEY `act` (`act`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_watch`
--

CREATE TABLE IF NOT EXISTS `fd_watch` (
  `id` int(11) NOT NULL auto_increment,
  `user` int(11) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `user` (`user`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fd_words_relations`
--

CREATE TABLE IF NOT EXISTS `fd_words_relations` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `id_parent` int(10) unsigned NOT NULL default '0',
  `id_child` int(10) unsigned NOT NULL default '0',
  `id_state` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fdfolders`
--

CREATE TABLE IF NOT EXISTS `fdfolders` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `flname` varchar(100) NOT NULL default '',
  `user` int(10) unsigned NOT NULL default '0',
  `d_cr` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`),
  KEY `flname` (`flname`),
  KEY `user` (`user`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fdmail`
--

CREATE TABLE IF NOT EXISTS `fdmail` (
  `id` int(11) NOT NULL auto_increment,
  `d_cr` datetime NOT NULL default '0000-00-00 00:00:00',
  `d_snt` datetime default NULL,
  `d_rcv` datetime default NULL,
  `sndr` int(11) NOT NULL default '0',
  `rcvr` int(11) NOT NULL default '0',
  `head` varchar(255) binary NOT NULL default '',
  `body` text NOT NULL,
  `del_s` tinyint(4) NOT NULL default '0',
  `del_r` tinyint(4) NOT NULL default '0',
  `d_read` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `d_cr` (`d_cr`),
  KEY `d_snt` (`d_snt`),
  KEY `d_rsv` (`d_rcv`),
  KEY `from` (`sndr`),
  KEY `to` (`rcvr`),
  KEY `del_r` (`del_r`),
  KEY `del_s` (`del_s`),
  KEY `d_read` (`d_read`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fdtranzit`
--

CREATE TABLE IF NOT EXISTS `fdtranzit` (
  `id` int(11) NOT NULL auto_increment,
  `title` int(11) NOT NULL default '0',
  `user` int(11) NOT NULL default '0',
  `folder` int(11) NOT NULL default '0',
  `d_cr` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`),
  KEY `title` (`title`),
  KEY `folder` (`folder`),
  KEY `title_2` (`user`,`folder`,`title`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fdviews`
--

CREATE TABLE IF NOT EXISTS `fdviews` (
  `id` int(11) NOT NULL auto_increment,
  `user` int(11) NOT NULL default '0',
  `name` varchar(100) NOT NULL default '',
  `d_cr` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`),
  KEY `user` (`user`),
  KEY `name` (`name`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `fdvtranzit`
--

CREATE TABLE IF NOT EXISTS `fdvtranzit` (
  `id` int(11) NOT NULL auto_increment,
  `view` int(11) NOT NULL default '0',
  `folder` int(11) NOT NULL default '0',
  `d_cr` datetime NOT NULL default '0000-00-00 00:00:00',
  `user` int(11) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `view` (`view`),
  KEY `folder` (`folder`),
  KEY `user` (`user`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `ignor`
--

CREATE TABLE IF NOT EXISTS `ignor` (
  `id` int(11) NOT NULL auto_increment,
  `type` tinyint(4) NOT NULL default '0',
  `user` int(11) NOT NULL default '0',
  `ignor` int(11) NOT NULL default '0',
  `begin` datetime NOT NULL default '0000-00-00 00:00:00',
  `end` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`),
  KEY `ignor` (`ignor`),
  KEY `begin` (`begin`),
  KEY `type_2` (`user`,`type`,`end`,`ignor`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `quest`
--

CREATE TABLE IF NOT EXISTS `quest` (
  `id` int(11) NOT NULL auto_increment,
  `head` int(11) NOT NULL default '0',
  `numb` tinyint(3) unsigned NOT NULL default '0',
  `node` varchar(255) binary NOT NULL default '',
  `gol` int(10) unsigned NOT NULL default '0',
  `type` tinyint(3) unsigned NOT NULL default '0',
  `user` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `head` (`head`),
  KEY `numb` (`numb`),
  KEY `user` (`user`),
  KEY `type` (`type`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `robots`
--

CREATE TABLE IF NOT EXISTS `robots` (
  `id` int(11) NOT NULL auto_increment,
  `ip` varchar(15) default NULL,
  `user` int(11) default NULL,
  `time` timestamp NOT NULL,
  `page` varchar(200) default NULL,
  `refer` varchar(200) default NULL,
  `action` tinyint(4) default NULL,
  `reefer` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `sd_rubriks`
--

CREATE TABLE IF NOT EXISTS `sd_rubriks` (
  `id` tinyint(4) NOT NULL auto_increment,
  `r_name` varchar(100) NOT NULL default '',
  `b_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `colon` tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `r_name` (`r_name`),
  KEY `b_date` (`b_date`),
  KEY `column` (`colon`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `ss`
--

CREATE TABLE IF NOT EXISTS `ss` (
  `id` tinyint(4) NOT NULL default '0',
  `id_post` int(11) NOT NULL default '0',
  `date_post` datetime NOT NULL default '0000-00-00 00:00:00',
  `block` tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `ss1`
--

CREATE TABLE IF NOT EXISTS `ss1` (
  `id` tinyint(4) NOT NULL default '0',
  `id_post` int(11) NOT NULL default '0',
  `date_post` datetime NOT NULL default '0000-00-00 00:00:00',
  `block` tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------
--
-- Структура таблицы `titles`
--

CREATE TABLE IF NOT EXISTS `titles` (
  `id` int(11) NOT NULL auto_increment,
  `auth` int(11) default NULL,
  `head` varchar(255) default NULL,
  `reg` datetime NOT NULL default '0000-00-00 00:00:00',
  `lposttime` datetime default NULL,
  `lpostuser` int(11) default NULL,
  `lpostnick` varchar(50) default NULL,
  `id_last_post` int(11) NOT NULL default '0',
  `seenid` int(11) unsigned NOT NULL default '0',
  `seenall` int(11) unsigned NOT NULL default '0',
  `dock` tinyint(4) NOT NULL default '0',
  `type` tinyint(4) NOT NULL default '0',
  `folder` int(11) NOT NULL default '1',
  `npost` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `type` (`type`),
  KEY `auth` (`auth`),
  KEY `lposttime` (`lposttime`),
  KEY `folder` (`folder`),
  KEY `id` (`auth`,`id`),
  KEY `id_2` (`id`,`dock`,`lposttime`),
  KEY `id_3` (`id`,`dock`,`lposttime`,`type`,`npost`,`seenid`,`seenall`,`reg`,`head`,`lpostuser`,`lpostnick`,`id_last_post`,`auth`),
  KEY `titles0001` (dock desc, lposttime desc)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL auto_increment,
  `nick` varchar(50) default NULL,
  `pass` varchar(50) default NULL,
  `mail` varchar(200) default NULL,
  `name` varchar(50) default NULL,
  `fam` varchar(50) default NULL,
  `sex` char(1) default NULL,
  `bith` date default NULL,
  `reg` timestamp NOT NULL,
  `pass2` varchar(50) default NULL,
  `smail` tinyint(3) unsigned NOT NULL default '0',
  `sname` tinyint(3) unsigned NOT NULL default '0',
  `city` varchar(40) NOT NULL default '',
  `scity` tinyint(3) unsigned NOT NULL default '0',
  `country` varchar(40) NOT NULL default '',
  `scountry` tinyint(1) unsigned NOT NULL default '0',
  `ssex` tinyint(1) unsigned NOT NULL default '0',
  `icq` varchar(9) NOT NULL default '',
  `sicq` tinyint(3) unsigned NOT NULL default '0',
  `sbith` tinyint(3) unsigned NOT NULL default '0',
  `lang` tinyint(3) unsigned NOT NULL default '0',
  `h_ip` tinyint(3) unsigned NOT NULL default '0',
  `view_def` tinyint(4) NOT NULL default '1',
  `pp_def` tinyint(4) NOT NULL default '30',
  `pt_def` tinyint(4) NOT NULL default '40',
  `avatar` varchar(200) NOT NULL default '',
  `s_avatar` tinyint(4) NOT NULL default '0',
  `ok_avatar` tinyint(4) NOT NULL default '0',
  `v_avatars` tinyint(4) NOT NULL default '1',
  `fd_timezone` tinyint(4) NOT NULL default '16',
  `footer` varchar(255) NOT NULL default '',
  `ban` tinyint(4) NOT NULL default '0',
  `activate_code` int(11) NOT NULL default '0',
  `is_active` tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `pass` (`pass`),
  KEY `pass2` (`pass2`),
  KEY `s_avatar` (`s_avatar`),
  KEY `ok_avatar` (`ok_avatar`),
  KEY `v_avatars` (`v_avatars`),
  KEY `nick` (`nick`),
  KEY `id` (`id`,`nick`),
  KEY `ban` (`ban`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Структура таблицы `voice`
--

CREATE TABLE IF NOT EXISTS `voice` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `head` int(10) unsigned NOT NULL default '0',
  `node` int(10) unsigned NOT NULL default '0',
  `user` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `user` (`user`),
  KEY `head` (`head`),
  KEY `node` (`node`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;
-- --------------------------------------------------------

--
-- Структура таблицы `ip_address`
--

CREATE TABLE IF NOT EXISTS `ip_address` (
  `id` int(11) NOT NULL auto_increment,
  `ip` varchar(15) NOT NULL ,
  `spammer` boolean NOT NULL,
  `source` varchar(100) NOT NULL default '',
  `last_check` timestamp NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `ip` (`ip`, `spammer`)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ENGINE = InnoDB;
