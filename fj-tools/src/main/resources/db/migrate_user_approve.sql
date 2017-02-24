ALTER TABLE `users` ADD COLUMN `approved` TINYINT(1) NOT NULL DEFAULT 0 AFTER `is_active`;
UPDATE `users` SET `approved` = 1;