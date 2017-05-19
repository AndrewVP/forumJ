CREATE TABLE `request` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `methodId` tinyint(4) NOT NULL,
  `userId` bigint(20) NOT NULL DEFAULT '-1',
  `ipId` bigint(20) NOT NULL,
  `url` varchar(200) COLLATE utf8_bin NOT NULL,
  `time` bigint(20) NOT NULL,
  `uasId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_request_ipId_userId_time` (`userId`,`time`,`ipId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
  `bot` tinyint NOT NULL,
  `operationSystemId` BIGINT NOT NULL,
  `browserId` BIGINT NOT NULL,
  `deviceTypeId` BIGINT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `operation_system` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `browser` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `device_type` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));


ALTER TABLE `ip_address`
  CHANGE COLUMN `id` `id` BIGINT NOT NULL AUTO_INCREMENT ,
  CHANGE COLUMN `spammer` `spammer` TINYINT(1) NOT NULL DEFAULT 0 ,
  CHANGE COLUMN `source` `source` VARCHAR(100) NULL DEFAULT '' ,
  CHANGE COLUMN `last_check` `last_check` TIMESTAMP NULL;
