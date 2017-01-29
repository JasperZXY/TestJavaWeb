
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

INSERT INTO `resource` (`id`, `name`, `type`, `parent_id`, `status`) VALUES
	(1, '权限相关', 'nav', 0, 0),
	(1001, '资源访问', 'menu', 1, 0),
	(1002, '资源新增', 'button', 1001, 0),
	(1003, '资源更新', 'button', 1001, 2),
	(1005, '资源禁用/解禁', 'button', 1001, 2),
	(2001, '角色访问', 'menu', 1, 0),
	(2002, '角色新增', 'button', 2001, 0),
	(2003, '角色更新', 'button', 2001, 0),
	(2005, '角色禁用/解禁', 'button', 2001, 0),
	(2006, '给角色分配资源', 'button', 2001, 0),
	(2007, '给用户指定角色', 'menu', 3001, 0),
	(3001, '用户管理', 'menu', 0, 0),
	(3002, '用户新增', 'button', 3001, 0),
	(3003, '用户修改', 'button', 3001, 0),
	(3004, '用户删除', 'button', 3001, 0),
	(3005, '用户冻结/解冻', 'button', 3001, 0),
	(3006, '协助修改用户密码', 'button', 3001, 0);

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
  `resource_ids` varchar(1024) DEFAULT NULL COMMENT '资源对应ID集合',
  `create_uid` int(11) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与资源的关系';

-- 导出  表 tjw_admin_permission.user_role_relation 结构
CREATE TABLE IF NOT EXISTS `user_role_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户对应的ID',
  `role_id` int(11) NOT NULL COMMENT '角色对应的ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色的关系';
