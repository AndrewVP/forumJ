-- MySQL dump 10.13  Distrib 5.5.19, for Linux (x86_64)
--
-- Host: localhost    Database: rt5gf33
-- ------------------------------------------------------
-- Server version	5.5.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `body`
--

DROP TABLE IF EXISTS `body`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `body` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `head` int(11) DEFAULT NULL,
  `fd_state` tinyint(4) NOT NULL DEFAULT '0',
  `table_post` varchar(10) NOT NULL DEFAULT 'body',
  `table_head` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `head` (`head`),
  KEY `id_count` (`id`,`head`),
  KEY `fd_state` (`fd_state`),
  KEY `count2` (`head`,`id`)
) ENGINE=InnoDB AUTO_INCREMENT=731953 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_action`
--

DROP TABLE IF EXISTS `fd_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fd_ip` varchar(15) DEFAULT NULL,
  `fd_subnet` varchar(15) DEFAULT NULL,
  `fd_user` int(11) DEFAULT NULL,
  `fd_time` datetime DEFAULT NULL,
  `fd_page` varchar(200) DEFAULT NULL,
  `fd_refer` varchar(300) DEFAULT NULL,
  `fd_action` tinyint(4) DEFAULT NULL,
  `fd_reefer` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fd_user` (`fd_user`),
  KEY `fd_subnet` (`fd_subnet`),
  KEY `fd_time` (`fd_time`),
  KEY `fd_ip` (`fd_ip`),
  KEY `fd_reefer` (`fd_reefer`)
) ENGINE=InnoDB AUTO_INCREMENT=5195321 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_head001`
--

DROP TABLE IF EXISTS `fd_head001`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_head001` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` int(11) DEFAULT NULL,
  `tilte` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(15) NOT NULL DEFAULT '',
  `domen` varchar(100) NOT NULL DEFAULT '',
  `outd` varchar(100) NOT NULL DEFAULT '',
  `nred` tinyint(4) NOT NULL DEFAULT '0',
  `fd_post_time` bigint(20) NOT NULL,
  `fd_post_edit_time` bigint(20) DEFAULT '0',
  `id_post` int(11) NOT NULL DEFAULT '0',
  `thread_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `auth` (`auth`),
  KEY `thread_id` (`thread_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_head002`
--

DROP TABLE IF EXISTS `fd_head002`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_head002` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` int(11) DEFAULT NULL,
  `tilte` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(15) NOT NULL DEFAULT '',
  `domen` varchar(100) NOT NULL DEFAULT '',
  `outd` varchar(100) NOT NULL DEFAULT '',
  `nred` tinyint(4) NOT NULL DEFAULT '0',
  `fd_post_time` bigint(20) NOT NULL,
  `fd_post_edit_time` bigint(20) DEFAULT '0',
  `id_post` int(11) NOT NULL DEFAULT '0',
  `thread_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `auth` (`auth`),
  KEY `thread_id` (`thread_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_head003`
--

DROP TABLE IF EXISTS `fd_head003`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_head003` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` int(11) DEFAULT NULL,
  `tilte` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(15) NOT NULL DEFAULT '',
  `domen` varchar(100) NOT NULL DEFAULT '',
  `outd` varchar(100) NOT NULL DEFAULT '',
  `nred` tinyint(4) NOT NULL DEFAULT '0',
  `fd_post_time` bigint(20) NOT NULL,
  `fd_post_edit_time` bigint(20) DEFAULT '0',
  `id_post` int(11) NOT NULL DEFAULT '0',
  `thread_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `auth` (`auth`),
  KEY `thread_id` (`thread_id`)
) ENGINE=InnoDB AUTO_INCREMENT=120000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_head004`
--

DROP TABLE IF EXISTS `fd_head004`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_head004` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` int(11) DEFAULT NULL,
  `tilte` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(15) NOT NULL DEFAULT '',
  `domen` varchar(100) NOT NULL DEFAULT '',
  `outd` varchar(100) NOT NULL DEFAULT '',
  `nred` tinyint(4) NOT NULL DEFAULT '0',
  `fd_post_time` bigint(20) NOT NULL,
  `fd_post_edit_time` bigint(20) DEFAULT '0',
  `id_post` int(11) NOT NULL DEFAULT '0',
  `thread_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `auth` (`auth`),
  KEY `thread_id` (`thread_id`)
) ENGINE=InnoDB AUTO_INCREMENT=160000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_head005`
--

DROP TABLE IF EXISTS `fd_head005`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_head005` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` int(11) DEFAULT NULL,
  `tilte` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(15) NOT NULL DEFAULT '',
  `domen` varchar(100) NOT NULL DEFAULT '',
  `outd` varchar(100) NOT NULL DEFAULT '',
  `nred` tinyint(4) NOT NULL DEFAULT '0',
  `fd_post_time` bigint(20) NOT NULL,
  `fd_post_edit_time` bigint(20) DEFAULT '0',
  `id_post` int(11) NOT NULL DEFAULT '0',
  `thread_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `auth` (`auth`),
  KEY `thread_id` (`thread_id`)
) ENGINE=InnoDB AUTO_INCREMENT=200000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_head006`
--

DROP TABLE IF EXISTS `fd_head006`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_head006` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` int(11) DEFAULT NULL,
  `tilte` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(15) NOT NULL DEFAULT '',
  `domen` varchar(100) NOT NULL DEFAULT '',
  `outd` varchar(100) NOT NULL DEFAULT '',
  `nred` tinyint(4) NOT NULL DEFAULT '0',
  `fd_post_time` bigint(20) NOT NULL,
  `fd_post_edit_time` bigint(20) DEFAULT '0',
  `id_post` int(11) NOT NULL DEFAULT '0',
  `thread_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `auth` (`auth`),
  KEY `thread_id` (`thread_id`)
) ENGINE=InnoDB AUTO_INCREMENT=240000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_head007`
--

DROP TABLE IF EXISTS `fd_head007`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_head007` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` int(11) DEFAULT NULL,
  `tilte` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(15) NOT NULL DEFAULT '',
  `domen` varchar(100) NOT NULL DEFAULT '',
  `outd` varchar(100) NOT NULL DEFAULT '',
  `nred` tinyint(4) NOT NULL DEFAULT '0',
  `fd_post_time` bigint(20) NOT NULL,
  `fd_post_edit_time` bigint(20) DEFAULT '0',
  `id_post` int(11) NOT NULL DEFAULT '0',
  `thread_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `auth` (`auth`),
  KEY `thread_id` (`thread_id`)
) ENGINE=InnoDB AUTO_INCREMENT=280000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_head008`
--

DROP TABLE IF EXISTS `fd_head008`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_head008` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` int(11) DEFAULT NULL,
  `tilte` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(15) NOT NULL DEFAULT '',
  `domen` varchar(100) NOT NULL DEFAULT '',
  `outd` varchar(100) NOT NULL DEFAULT '',
  `nred` tinyint(4) NOT NULL DEFAULT '0',
  `fd_post_time` bigint(20) NOT NULL,
  `fd_post_edit_time` bigint(20) DEFAULT '0',
  `id_post` int(11) NOT NULL DEFAULT '0',
  `thread_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `auth` (`auth`),
  KEY `thread_id` (`thread_id`)
) ENGINE=InnoDB AUTO_INCREMENT=320000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_head009`
--

DROP TABLE IF EXISTS `fd_head009`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_head009` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` int(11) DEFAULT NULL,
  `tilte` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(15) NOT NULL DEFAULT '',
  `domen` varchar(100) NOT NULL DEFAULT '',
  `outd` varchar(100) NOT NULL DEFAULT '',
  `nred` tinyint(4) NOT NULL DEFAULT '0',
  `fd_post_time` bigint(20) NOT NULL,
  `fd_post_edit_time` bigint(20) DEFAULT '0',
  `id_post` int(11) NOT NULL DEFAULT '0',
  `thread_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `auth` (`auth`),
  KEY `thread_id` (`thread_id`)
) ENGINE=InnoDB AUTO_INCREMENT=360000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_head010`
--

DROP TABLE IF EXISTS `fd_head010`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_head010` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` int(11) DEFAULT NULL,
  `tilte` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(15) NOT NULL DEFAULT '',
  `domen` varchar(100) NOT NULL DEFAULT '',
  `outd` varchar(100) NOT NULL DEFAULT '',
  `nred` tinyint(4) NOT NULL DEFAULT '0',
  `fd_post_time` bigint(20) NOT NULL,
  `fd_post_edit_time` bigint(20) DEFAULT '0',
  `id_post` int(11) NOT NULL DEFAULT '0',
  `thread_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `auth` (`auth`),
  KEY `thread_id` (`thread_id`)
) ENGINE=InnoDB AUTO_INCREMENT=399423 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_head011`
--

DROP TABLE IF EXISTS `fd_head011`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_head011` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` int(11) DEFAULT NULL,
  `tilte` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(15) NOT NULL DEFAULT '',
  `domen` varchar(100) NOT NULL DEFAULT '',
  `outd` varchar(100) NOT NULL DEFAULT '',
  `nred` tinyint(4) NOT NULL DEFAULT '0',
  `fd_post_time` bigint(20) NOT NULL,
  `fd_post_edit_time` bigint(20) DEFAULT '0',
  `id_post` int(11) NOT NULL DEFAULT '0',
  `thread_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `auth` (`auth`),
  KEY `thread_id` (`thread_id`)
) ENGINE=InnoDB AUTO_INCREMENT=588067 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_head012`
--

DROP TABLE IF EXISTS `fd_head012`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_head012` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` int(11) DEFAULT NULL,
  `tilte` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(15) NOT NULL DEFAULT '',
  `domen` varchar(100) NOT NULL DEFAULT '',
  `outd` varchar(100) NOT NULL DEFAULT '',
  `nred` tinyint(4) NOT NULL DEFAULT '0',
  `fd_post_time` bigint(20) NOT NULL,
  `fd_post_edit_time` bigint(20) DEFAULT '0',
  `id_post` int(11) NOT NULL DEFAULT '0',
  `thread_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `auth` (`auth`),
  KEY `thread_id` (`thread_id`),
  KEY `id1` (`fd_post_time`,`thread_id`,`auth`,`id`)
) ENGINE=InnoDB AUTO_INCREMENT=731953 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_post001`
--

DROP TABLE IF EXISTS `fd_post001`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_post001` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL DEFAULT '0',
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_post002`
--

DROP TABLE IF EXISTS `fd_post002`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_post002` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL DEFAULT '0',
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_post003`
--

DROP TABLE IF EXISTS `fd_post003`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_post003` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL DEFAULT '0',
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=120000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_post004`
--

DROP TABLE IF EXISTS `fd_post004`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_post004` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL DEFAULT '0',
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=160000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_post005`
--

DROP TABLE IF EXISTS `fd_post005`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_post005` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL DEFAULT '0',
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_post006`
--

DROP TABLE IF EXISTS `fd_post006`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_post006` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL DEFAULT '0',
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=240000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_post007`
--

DROP TABLE IF EXISTS `fd_post007`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_post007` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL DEFAULT '0',
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=280000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_post008`
--

DROP TABLE IF EXISTS `fd_post008`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_post008` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL DEFAULT '0',
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=320000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_post009`
--

DROP TABLE IF EXISTS `fd_post009`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_post009` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL DEFAULT '0',
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=360000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_post010`
--

DROP TABLE IF EXISTS `fd_post010`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_post010` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL DEFAULT '0',
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=399423 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_post011`
--

DROP TABLE IF EXISTS `fd_post011`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_post011` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL DEFAULT '0',
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=588067 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_post012`
--

DROP TABLE IF EXISTS `fd_post012`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_post012` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL DEFAULT '0',
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=731953 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_setup`
--

DROP TABLE IF EXISTS `fd_setup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_setup` (
  `curret_body_table` char(10) NOT NULL DEFAULT '',
  `current_body_head_table` char(10) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_subscribe`
--

DROP TABLE IF EXISTS `fd_subscribe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_subscribe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL DEFAULT '0',
  `title` int(11) NOT NULL DEFAULT '0',
  `d_start` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `d_end` datetime NOT NULL DEFAULT '2100-01-01 00:00:00',
  `kod` int(11) NOT NULL DEFAULT '0',
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `act` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `user` (`user`),
  KEY `title` (`title`),
  KEY `d_start` (`d_start`),
  KEY `d_end` (`d_end`),
  KEY `kod` (`kod`),
  KEY `type` (`type`),
  KEY `act` (`act`)
) ENGINE=InnoDB AUTO_INCREMENT=311 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fd_watch`
--

DROP TABLE IF EXISTS `fd_watch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fd_watch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fdfolders`
--

DROP TABLE IF EXISTS `fdfolders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fdfolders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `flname` varchar(100) NOT NULL DEFAULT '',
  `user` int(10) unsigned NOT NULL DEFAULT '0',
  `d_cr` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `flname` (`flname`),
  KEY `user` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fdmail`
--

DROP TABLE IF EXISTS `fdmail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fdmail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `d_cr` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `d_snt` datetime DEFAULT NULL,
  `d_rcv` datetime DEFAULT NULL,
  `sndr` int(11) NOT NULL DEFAULT '0',
  `rcvr` int(11) NOT NULL DEFAULT '0',
  `head` varchar(255) NOT NULL DEFAULT '',
  `body` text NOT NULL,
  `del_s` tinyint(4) NOT NULL DEFAULT '0',
  `del_r` tinyint(4) NOT NULL DEFAULT '0',
  `d_read` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `d_cr` (`d_cr`),
  KEY `d_snt` (`d_snt`),
  KEY `d_rsv` (`d_rcv`),
  KEY `from` (`sndr`),
  KEY `to` (`rcvr`),
  KEY `del_r` (`del_r`),
  KEY `del_s` (`del_s`),
  KEY `d_read` (`d_read`)
) ENGINE=InnoDB AUTO_INCREMENT=7030 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fdtranzit`
--

DROP TABLE IF EXISTS `fdtranzit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fdtranzit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` int(11) NOT NULL DEFAULT '0',
  `user` int(11) NOT NULL DEFAULT '0',
  `folder` int(11) NOT NULL DEFAULT '0',
  `d_cr` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `title` (`title`),
  KEY `folder` (`folder`),
  KEY `title_2` (`user`,`folder`,`title`)
) ENGINE=InnoDB AUTO_INCREMENT=43804 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fdviews`
--

DROP TABLE IF EXISTS `fdviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fdviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) NOT NULL DEFAULT '',
  `d_cr` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `user` (`user`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fdvtranzit`
--

DROP TABLE IF EXISTS `fdvtranzit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fdvtranzit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `view` int(11) NOT NULL DEFAULT '0',
  `folder` int(11) NOT NULL DEFAULT '0',
  `d_cr` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `user` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `view` (`view`),
  KEY `folder` (`folder`),
  KEY `user` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ignor`
--

DROP TABLE IF EXISTS `ignor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ignor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `user` int(11) NOT NULL DEFAULT '0',
  `ignor` int(11) NOT NULL DEFAULT '0',
  `begin` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `ignor` (`ignor`),
  KEY `begin` (`begin`),
  KEY `type_2` (`user`,`type`,`end`,`ignor`),
  KEY `id2` (`ignor`,`user`,`end`)
) ENGINE=InnoDB AUTO_INCREMENT=10665 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ip_address`
--

DROP TABLE IF EXISTS `ip_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(15) NOT NULL,
  `spammer` tinyint(1) NOT NULL,
  `source` varchar(100) NOT NULL DEFAULT '',
  `last_check` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `ip` (`ip`,`spammer`)
) ENGINE=InnoDB AUTO_INCREMENT=2487 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quest`
--

DROP TABLE IF EXISTS `quest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `head` int(11) NOT NULL DEFAULT '0',
  `numb` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `node` varchar(255) NOT NULL DEFAULT '',
  `gol` int(10) unsigned NOT NULL DEFAULT '0',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `user` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `head` (`head`),
  KEY `numb` (`numb`),
  KEY `user` (`user`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=7367 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `robots`
--

DROP TABLE IF EXISTS `robots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `robots` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(15) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `page` varchar(200) DEFAULT NULL,
  `refer` varchar(200) DEFAULT NULL,
  `action` tinyint(4) DEFAULT NULL,
  `reefer` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1185358 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `titles`
--

DROP TABLE IF EXISTS `titles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `titles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` int(11) DEFAULT NULL,
  `head` varchar(255) DEFAULT NULL,
  `reg` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `lposttime` datetime DEFAULT NULL,
  `lpostuser` int(11) DEFAULT NULL,
  `lpostnick` varchar(50) DEFAULT NULL,
  `id_last_post` int(11) NOT NULL DEFAULT '0',
  `seenid` int(11) unsigned NOT NULL DEFAULT '0',
  `seenall` int(11) unsigned NOT NULL DEFAULT '0',
  `dock` tinyint(4) NOT NULL DEFAULT '0',
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `folder` int(11) NOT NULL DEFAULT '1',
  `npost` int(11) NOT NULL DEFAULT '1',
  `closed` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `type` (`type`),
  KEY `auth` (`auth`),
  KEY `lposttime` (`lposttime`),
  KEY `folder` (`folder`),
  KEY `id` (`auth`,`id`),
  KEY `id_2` (`id`,`dock`,`lposttime`),
  KEY `id_3` (`id`,`dock`,`lposttime`,`type`,`npost`,`seenid`,`seenall`,`reg`,`head`,`lpostuser`,`lpostnick`,`id_last_post`,`auth`),
  KEY `titles0001` (`dock`,`lposttime`)
) ENGINE=InnoDB AUTO_INCREMENT=44847 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick` varchar(50) DEFAULT NULL,
  `pass` varchar(50) DEFAULT NULL,
  `mail` varchar(200) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `fam` varchar(50) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `bith` date DEFAULT NULL,
  `reg` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pass2` varchar(50) DEFAULT NULL,
  `smail` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `sname` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `city` varchar(40) NOT NULL DEFAULT '',
  `scity` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `country` varchar(40) NOT NULL DEFAULT '',
  `scountry` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `ssex` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `icq` varchar(9) NOT NULL DEFAULT '',
  `sicq` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `sbith` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `lang` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `h_ip` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `view_def` tinyint(4) NOT NULL DEFAULT '1',
  `pp_def` tinyint(4) NOT NULL DEFAULT '30',
  `pt_def` tinyint(4) NOT NULL DEFAULT '40',
  `avatar` varchar(200) NOT NULL DEFAULT '',
  `s_avatar` tinyint(4) NOT NULL DEFAULT '0',
  `ok_avatar` tinyint(4) NOT NULL DEFAULT '0',
  `v_avatars` tinyint(4) NOT NULL DEFAULT '1',
  `fd_timezone` tinyint(4) NOT NULL DEFAULT '16',
  `footer` varchar(255) NOT NULL DEFAULT '',
  `ban` tinyint(4) NOT NULL DEFAULT '0',
  `activate_code` int(11) NOT NULL DEFAULT '0',
  `is_active` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `pass` (`pass`),
  KEY `pass2` (`pass2`),
  KEY `s_avatar` (`s_avatar`),
  KEY `ok_avatar` (`ok_avatar`),
  KEY `v_avatars` (`v_avatars`),
  KEY `nick` (`nick`),
  KEY `id` (`id`,`nick`),
  KEY `ban` (`ban`)
) ENGINE=InnoDB AUTO_INCREMENT=1579 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `voice`
--

DROP TABLE IF EXISTS `voice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voice` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `head` int(10) unsigned NOT NULL DEFAULT '0',
  `node` int(10) unsigned NOT NULL DEFAULT '0',
  `user` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user` (`user`),
  KEY `head` (`head`),
  KEY `node` (`node`)
) ENGINE=InnoDB AUTO_INCREMENT=13664 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-01-09 18:03:55
