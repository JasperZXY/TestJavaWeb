
ALTER TABLE `account`
	ADD COLUMN `email` VARCHAR(128) NULL COMMENT '邮箱' AFTER `id`,
	ADD UNIQUE INDEX `email` (`email`);

