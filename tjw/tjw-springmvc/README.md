## 使用技术
springmvc


## 运行
mvn jetty:run -Djetty.port=8083 -Pdev
mvn jetty:run -Djetty.port=8083 -Ppro


## 功能点
1. 请求接收参数，可支持把多个参数自动包装成对象
2. 使用InitBinder来处理Date类型的参数
3. 向前端传递参数
4. redirect方式
5. 文件上传
6. 异常的处理：处理局部异常（Controller内）、处理全局异常（所有Controller）
7. 支持通过命令行来加载不同配置文件


## Spring MVC集成slf4j-logback
1. 添加相应jar包
2. 编写logback.xml
3. 配置web.xml


## 部分接口
http://127.0.0.1:8083/tjw-springmvc/user/hello
http://127.0.0.1:8083/tjw-springmvc/user/string/login?username=Jasper&password=12
http://127.0.0.1:8083/tjw-springmvc/user/string/login?username=admin&password=123456
http://127.0.0.1:8083/tjw-springmvc/user/login?username=admin&password=123456
http://127.0.0.1:8083/tjw-springmvc/user/json/login?username=admin&password=123456
http://127.0.0.1:8083/tjw-springmvc/static/index.html
http://127.0.0.1:8083/tjw-springmvc/user/date?date=2008-01-01
http://127.0.0.1:8083/tjw-springmvc/user/result
http://127.0.0.1:8083/tjw-springmvc/user/uploadfileUI
http://127.0.0.1:8083/tjw-springmvc/same/test/1
http://127.0.0.1:8083/tjw-springmvc/same/test/2
