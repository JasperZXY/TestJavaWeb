-- 导出 tjw_admin_permission 的数据库结构
CREATE DATABASE IF NOT EXISTS `tjw_admin_permission` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tjw_admin_permission`;


-- 导出  表 tjw_admin_permission.group 结构
CREATE TABLE IF NOT EXISTS `group` (
  `id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT '名称',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户分组';

-- 导出  表 tjw_admin_permission.resource 结构
CREATE TABLE IF NOT EXISTS `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- 导出  表 tjw_admin_permission.role_resource_relation 结构
CREATE TABLE IF NOT EXISTS `role_resource_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色对应ID',
  `resource_id` int(11) NOT NULL COMMENT '资源对应ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与资源的关系';

-- 导出  表 tjw_admin_permission.user_group_relation 结构
CREATE TABLE IF NOT EXISTS `user_group_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户对应的ID',
  `group_id` int(11) NOT NULL COMMENT '分组对应的ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与分组的关系';

