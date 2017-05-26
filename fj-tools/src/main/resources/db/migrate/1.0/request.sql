CREATE TABLE `request` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `methodId` tinyint NOT NULL,
  `userId` bigint NOT NULL DEFAULT '-1',
  `ipId` bigint NOT NULL,
  `url` varchar(200) COLLATE utf8_bin NOT NULL,
  `time` bigint NOT NULL,
  `uasId` int NOT NULL,
  `botId` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_request_ipId_userId_time` (`userId`,`time`,`ipId`)
) ENGINE=InnoDB AUTO_INCREMENT=625 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `http_header_name` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));

CREATE TABLE `http_cookie_name` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `http_header` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `requestId` BIGINT NOT NULL,
  `nameId` BIGINT NOT NULL,
  `value` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `http_cookie` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `requestId` BIGINT NOT NULL,
  `nameId` BIGINT NOT NULL,
  `value` VARCHAR(300) NULL,
  `domain` VARCHAR(200) NULL,
  `path` VARCHAR(200) NULL,
  `maxAge` INT NOT NULL DEFAULT 0,
  `secure` TINYINT NOT NULL DEFAULT 0,
  `version` INT NOT NULL DEFAULT 1,
  `httpOnly` TINYINT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `user_agent_string` (
  `id` bigint NOT NULL  AUTO_INCREMENT,
  `value` varchar(300) COLLATE utf8_bin NOT NULL,
  `botId` int NOT NULL,
  `operationSystemId` BIGINT NOT NULL,
  `browserId` BIGINT NOT NULL,
  `deviceType` TINYINT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `operation_system` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `operation_system` (`name`)VALUES('NO OS');
UPDATE `operation_system` SET `id`='0' WHERE `name`='NO OS';
INSERT INTO `operation_system` (`name`)VALUES('UNKNOWN OS');
UPDATE `operation_system` SET `id`='1' WHERE `name`='UNKNOWN OS';

CREATE TABLE `browser` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `browser` (`name`) VALUES ('NO BROWSER');
UPDATE `browser` SET `id`='0' WHERE `name`='NO BROWSER';
INSERT INTO `browser` (`name`) VALUES ('UNKNOWN BROWSER');
UPDATE `browser` SET `id`='1' WHERE `name`='UNKNOWN BROWSER';

CREATE TABLE `bot` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `vendorId` INT NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `bot`(`name`,`vendorId`) VALUES('NO_BOT', 0);
UPDATE `bot` SET `id` = 0 WHERE `name` = 'NO_BOT';

CREATE TABLE `bot_vendor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  PRIMARY KEY (`id`));

INSERT INTO `bot_vendor` (`name`) VALUES ('UNKNOWN');
UPDATE `bot_vendor` SET `id` = 0 WHERE `name` = 'UNKNOWN';

ALTER TABLE `ip_address`
  CHANGE COLUMN `id` `id` BIGINT NOT NULL AUTO_INCREMENT ,
  CHANGE COLUMN `spammer` `spammer` TINYINT(1) NOT NULL DEFAULT 0 ,
  CHANGE COLUMN `source` `source` VARCHAR(100) NULL DEFAULT '' ,
  CHANGE COLUMN `last_check` `last_check` TIMESTAMP NULL;
