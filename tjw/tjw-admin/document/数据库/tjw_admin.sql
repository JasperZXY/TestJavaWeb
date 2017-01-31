
-- 导出 tjw_admin 的数据库结构
CREATE DATABASE IF NOT EXISTS `tjw_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tjw_admin`;


-- 导出  表 tjw_admin.account 结构
CREATE TABLE IF NOT EXISTS `account` (
  `id` varchar(64) NOT NULL COMMENT '账号名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(64) NOT NULL COMMENT '密码加密盐',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态 （0可用，1删除，2冻结）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账号';

INSERT INTO `account` (`id`, `password`, `salt`, `status`) VALUES
	('root', 'b72785c8842eae13a3d063449478e60c8a81ccb9449695d6fce252aaf43b4085', '50f92d1d620e91766c9cbf3dd90324fc', 0)
	;

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

INSERT INTO `user` (`id`, `account_id`, `name`, `birthday`, `createtime`, `status`) VALUES
	(1, 'root', 'root用户', NULL, '2017-01-18 19:08:44', 0)
	;

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


