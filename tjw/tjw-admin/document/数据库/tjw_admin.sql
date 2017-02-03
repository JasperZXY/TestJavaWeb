
-- 导出 tjw_admin 的数据库结构
CREATE DATABASE IF NOT EXISTS `tjw_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tjw_admin`;

-- 导出  表 tjw_admin.account 结构
CREATE TABLE IF NOT EXISTS `account` (
  `id` varchar(64) NOT NULL COMMENT '账号名',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(64) NOT NULL COMMENT '密码加密盐',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态 （0可用，1删除，2冻结）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账号';

-- 导出  表 tjw_admin.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account_id` varchar(64) NOT NULL COMMENT '账号对应ID',
  `name` varchar(64) DEFAULT NULL COMMENT '用户昵称',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态（0可用，1删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- loginfo
CREATE TABLE IF NOT EXISTS `loginfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `code` varchar(128) NOT NULL COMMENT '操作编码',
  `operation` varchar(64) NOT NULL COMMENT '操作',
  `target` varchar(1024) DEFAULT NULL COMMENT '被操作对象',
  `ip` varchar(16) NOT NULL COMMENT 'IP',
  `extra` varchar(1024) DEFAULT NULL COMMENT '额外信息',
  `uid` int(11) NOT NULL COMMENT '操作人',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';


