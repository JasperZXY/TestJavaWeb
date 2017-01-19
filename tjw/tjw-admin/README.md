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


## 运行
- mvn jetty:run -Djetty.port=9696 -Pdev
- mvn jetty:run -Djetty.port=9696 -Ppro


## 部分接口与页面
- http://127.0.0.1:9696/static/page/index2.html
- http://127.0.0.1:9696
- http://127.0.0.1:9696/demo/1
- http://127.0.0.1:9696/demo/2
- http://127.0.0.1:9696/demo/demo2string

## 更新日志
### 2016-01-19
1. 支持基本登录拦截
2. 支持基础权限拦截
3. 资源管理



