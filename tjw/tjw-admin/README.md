## 使用技术
### 后端
springmvc

### 前端
- [AdminLTE](https://www.awesomes.cn/repo/almasaeed2010/adminlte)
- [bootstrap](http://www.bootcss.com/)
- [jQuery](http://jquery.com/)


## 说明
1. AdminLTE对应的index2.html拆分为template.jsp、menu.jsp及具体的业务JSP页面
2. 权限模块需要注意一下，虽然定义了resource表结构，但是没有跟前端的页面进行整合，也即没有通过resource表中的数据来生成前端的菜单，只做了简单的控制
3. 目前权限对应的资源层级关系只实现了nav-->menu-->button，只要有对应的menu权限，nav即可展示


## TODO
1. permission包需优化，最后做到权限控制与业务分开
2. 权限分配可以考虑正则表达式
3. 考虑给单个人分配权限
4. 账号添加邮箱等字段，用于用户找回密码用
5. 操作日志 + 简单日志展示


## 运行
`注意`：要先创建好数据库

- mvn jetty:run -Djetty.port=9696 -Pdev
- mvn jetty:run -Djetty.port=9696 -Ppro

## 更新日志
### 2017-01-19
1. 支持基本登录拦截
2. 支持基础权限拦截
3. 资源管理
4. 角色管理

### 2017-01-20
1. 项目运行初始化，资源、角色、用户对应角色
2. 角色对应资源分配界面实现
3. 给用户指定角色
4. 优化登录，登录错误提示更人性化
5. 管理员修改密码功能

### 2017-01-29
1. 提示对话框优化
2. 登录优化，登录完后跳转回登录前的位置
