## 使用技术
thrift

## 运行
mvn clean package
java -jar target/tjw-thrift-jar-with-dependencies.jar

## 组装运行
服务端类型 | 服务端transportFactory | 客户端类型 | 客户端是否多线程 | 客户端结果
---| ---| ---| ---| ---|
TThreadPoolServer | 未设置 | TSocket | N | 正常
TThreadPoolServer | 未设置 | TSocket | Y | 正常
TThreadPoolServer | 未设置 | TFramedTransport | Y | 没反应
TThreadPoolServer | TFramedTransport.Factory() | TSocket | N | org.apache.thrift.transport.TTransportException
TThreadPoolServer | TFramedTransport.Factory() | TSocket | Y | org.apache.thrift.transport.TTransportException
TThreadPoolServer | TFramedTransport.Factory() | TFramedTransport | Y | 正常
TSimpleServer | 未设置 | TSocket | N | 正常
TSimpleServer | 未设置 | TSocket | Y | 正常
TSimpleServer | 未设置 | TFramedTransport | Y | 没反应
TSimpleServer | TFramedTransport.Factory() | TSocket | N | org.apache.thrift.transport.TTransportException
TSimpleServer | TFramedTransport.Factory() | TSocket | Y | org.apache.thrift.transport.TTransportException
TSimpleServer | TFramedTransport.Factory() | TFramedTransport | Y | 正常

综上：客户端与服务端的transportFactory一致才能正常，与服务端类型无关。