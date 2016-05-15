## 使用技术
springmvc

## 运行
mvn jetty:run -Djetty.port=8083

## 功能点
1. 请求接收参数，可支持把多个参数自动包装成对象
2. 使用InitBinder来处理Date类型的参数
3. 向前端传递参数
4. redirect方式
5. 文件上传
6. 异常的处理：处理局部异常（Controller内）、处理全局异常（所有Controller）

## 部分接口
http://127.0.0.1:8083/tjw-springmvc/user/hello<br/>
http://127.0.0.1:8083/tjw-springmvc/user/string/login?username=Jasper&password=12<br/>
http://127.0.0.1:8083/tjw-springmvc/user/string/login?username=admin&password=123456<br/>
http://127.0.0.1:8083/tjw-springmvc/user/login?username=admin&password=123456<br/>
http://127.0.0.1:8083/tjw-springmvc/user/json/login?username=admin&password=123456<br/>
http://127.0.0.1:8083/tjw-springmvc/static/index.html<br/>
http://127.0.0.1:8083/tjw-springmvc/user/date?date=2008-01-01<br/>
http://127.0.0.1:8083/tjw-springmvc/user/result<br/>
http://127.0.0.1:8083/tjw-springmvc/user/uploadfileUI<br/>
