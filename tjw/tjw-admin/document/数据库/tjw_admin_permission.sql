
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

-- 正在导出表  tjw_admin_permission.resource 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` (`id`, `name`, `type`, `parent_id`, `status`) VALUES
	(1, '权限相关', 'nav', 0, 0),
	(1001, '资源访问', 'menu', 1, 0),
	(1002, '资源新增', 'button', 1001, 0),
	(1003, '资源修改', 'button', 1001, 0),
	(1004, '资源删除', 'button', 1001, 0),
	(2001, '角色访问', 'menu', 1, 0),
	(2002, '角色新增', 'button', 2001, 0),
	(2003, '角色修改', 'button', 2001, 0),
	(2004, '角色删除', 'button', 2001, 0);
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;


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
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- 导出  表 tjw_admin_permission.role_resource_relation 结构
CREATE TABLE IF NOT EXISTS `role_resource_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色对应ID',
  `resource_id` int(11) NOT NULL COMMENT '资源对应ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色与资源的关系';

-- 正在导出表  tjw_admin_permission.role_resource_relation 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `role_resource_relation` DISABLE KEYS */;
INSERT INTO `role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES
	(1, 1, 1),
	(2, 1, 1001),
	(3, 1, 1002),
	(4, 1, 1003),
	(5, 1, 1004),
	(6, 1, 2001),
	(7, 1, 2002),
	(8, 1, 2003),
	(9, 1, 2004);
/*!40000 ALTER TABLE `role_resource_relation` ENABLE KEYS */;


-- 导出  表 tjw_admin_permission.user_role_relation 结构
CREATE TABLE IF NOT EXISTS `user_role_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户对应的ID',
  `role_id` int(11) NOT NULL COMMENT '角色对应的ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户与角色的关系';

-- 正在导出表  tjw_admin_permission.user_role_relation 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_role_relation` DISABLE KEYS */;
INSERT INTO `user_role_relation` (`id`, `user_id`, `role_id`) VALUES
	(1, 1, 1);
/*!40000 ALTER TABLE `user_role_relation` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
