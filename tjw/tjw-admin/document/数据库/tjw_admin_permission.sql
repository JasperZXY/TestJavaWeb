
-- 导出 tjw_admin_permission 的数据库结构
CREATE DATABASE IF NOT EXISTS `tjw_admin_permission` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tjw_admin_permission`;

-- 导出  表 tjw_admin_permission.resource 结构
CREATE TABLE IF NOT EXISTS `resource` (
  `id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT '名称',
  `type` varchar(64) NOT NULL COMMENT '类型，nav，menu，button',
  `parent_id` int(11) NOT NULL COMMENT '父ID',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限资源';

-- 导出  表 tjw_admin_permission.role 结构
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '名称',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色';

-- 正在导出表  tjw_admin_permission.role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`, `status`) VALUES
	(1, 'Root', 0),
	(2, '开发人员', 0),
	(3, '管理人员', 0);

-- 导出  表 tjw_admin_permission.role_resource_relation 结构
CREATE TABLE IF NOT EXISTS `role_resource_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色对应ID',
  `resource_ids` varchar(1024) DEFAULT NULL COMMENT '资源对应ID集合',
  `create_uid` int(11) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与资源的关系'


-- 导出  表 tjw_admin_permission.user_role_relation 结构
CREATE TABLE IF NOT EXISTS `user_role_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户对应的ID',
  `role_id` int(11) NOT NULL COMMENT '角色对应的ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户与角色的关系';

/*!40000 ALTER TABLE `user_role_relation` DISABLE KEYS */;
INSERT INTO `user_role_relation` (`id`, `user_id`, `role_id`) VALUES
	(1, 1, 1);
