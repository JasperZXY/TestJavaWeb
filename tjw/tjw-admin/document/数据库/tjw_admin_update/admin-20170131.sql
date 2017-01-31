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

