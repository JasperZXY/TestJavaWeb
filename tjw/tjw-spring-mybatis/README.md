## MySQL创建语句
```MySQL
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8
```

## 功能
1. MyBatis相关操作
2. Junit测试
3. Mockito测试用例
4. Aspect


## 运行
mvn tomcat7:run -Dmaven.tomcat.port=8084 -Pdev -Dmaven.test.skip=true

## 问题
用jetty运行，访问老是404

## 接口
http://127.0.0.1:8084/tjw-spring-mybatis/user?id=2
