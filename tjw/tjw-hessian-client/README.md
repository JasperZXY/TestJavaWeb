## 使用技术
springmvc
hessian


## 说明
### 服务端
1. 配置hessian-server.xml，声明HessianServiceExporter，bean的name属性将用于请求路径
2. web.xml中配置DispatcherServlet，指定hessian-server.xml配置文件

### 客户端
1. 声明一个HessianProxyFactoryBean的bean即可，注意serviceUrl参数跟方法重载

### 注意
接口的定义，服务端跟客户端的包名跟类名可以不一样


## 运行
mvn jetty:run -Djetty.port=8091


## 接口
http://127.0.0.1:8091/tjw-hessian-client/test/add?a=1&b=3
http://127.0.0.1:8091/tjw-hessian-client/test/other
