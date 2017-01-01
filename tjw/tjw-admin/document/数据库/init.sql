CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(64) NOT NULL COMMENT '账号名，登录用',
  `password` varchar(64) NOT NULL COMMENT '密码，登录用',
  `name` varchar(64) DEFAULT NULL COMMENT '用户昵称',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态（0可用，1删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户（后续account跟password要分开）';