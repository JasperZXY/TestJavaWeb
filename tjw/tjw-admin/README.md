## 使用技术/框架/库
### 后端
- SpringMVC
- MyBatis
- Apache commons

### 前端
- [jQuery](http://jquery.com/)
- [bootstrap](http://www.bootcss.com/)
- [AdminLTE](https://www.awesomes.cn/repo/almasaeed2010/adminlte)
- [jQuery toast](https://github.com/kamranahmedse/jquery-toast-plugin)


## 说明
1. AdminLTE对应的index2.html拆分为template.jsp、menu.jsp及具体的业务JSP页面。
2. 功能新增：在jsp目录下新增功能页面，可模仿demo文件夹，同时在menu.jsp中添加相关菜单，
往数据库添加相关数据（可通过SQL或运行后在界面上操作或在InitService中进行配置）。
3. 权限模块需要注意一下，虽然定义了resource表结构，但是没有跟前端的页面进行整合，
也即没有通过resource表中的数据来生成前端的菜单，只做了简单的控制。
4. 目前权限对应的资源层级关系只实现了nav-->menu-->button，只要有对应的menu权限，nav即可展示。
button为按钮，如“删除”按钮对应的删除操作等；menu为左边菜单栏，如“用户管理”，用户列表展示；
nav为导航菜单，如“权限相关”，把所有与权限有关的都放在这里，“资源管理”、“角色管理”等，
只要其中一个有权限，则可以展示对应的nav。


## 权限部分
### Resource数据约定
menu、button类的用4位整数表示，前2位表示模块或分组，后2位表示操作（01访问，02新增，03修改，04删除）。
nav类用1~3位数字表示。

### role_resource_relation
改用新的设计方式，所有对应的resource_id用“,”放在一条记录里面，所以认最新一条记录，要让某个角色没权限，
可以让其对应的resource_ids为空即可。


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
