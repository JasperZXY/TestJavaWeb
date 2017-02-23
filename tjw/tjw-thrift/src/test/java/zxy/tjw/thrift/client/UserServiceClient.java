package zxy.tjw.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zxy.tjw.thrift.thrift.UserService;

import java.io.IOException;

/**
 * @author Jasper (zhongxianyao)
 */
public class UserServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceClient.class);

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9696;
    private static final int CLIENT_TIMEOUT = 3000;

    public static void main(String[] args) {
//        simple();
//        thread();
//        threadV2();
//        nonblocking();
        myPoolFactory();
    }

    private static void simple() {
        try {
            TTransport transport = new TSocket(SERVER_HOST, SERVER_PORT);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            UserService.Client client = new UserService.Client(protocol);
            // 调用服务方法
            for (int i = 1; i <= 2; i++) {
                final int cur = i;
                logger.debug("{} ping {} : {}", Thread.currentThread().getName(), cur, client.ping("测试 " + cur));
            }
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    private static void thread() {
        TTransport transport = new TFramedTransport(new TSocket(SERVER_HOST, SERVER_PORT));
//        TTransport transport = new TSocket(SERVER_HOST, SERVER_PORT);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        TProtocol protocol = new TBinaryProtocol(transport);
        UserService.Client client = new UserService.Client(protocol);
        Object lockObj = new Object();
        for (int i = 1; i <= 3; i++) {
            final int cur = i;
            new Thread(() -> {
                try {
                    synchronized (lockObj) {
                        logger.debug("{} ping {} : {}", Thread.currentThread().getName(), cur, client.ping("测试 " + cur));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println("thread end");
    }

    private static void threadV2() {
        for (int task = 1; task <= 3; task++) {
            thread();
//            simple();
        }

        System.out.println("thread v2 end");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // TODO 运行失败，只能成功一个，需修改
    private static void nonblocking() {
        UserService.AsyncClient client = null;
        try {
            TAsyncClientManager clientManager = new TAsyncClientManager();
            TNonblockingTransport transport = new TNonblockingSocket(SERVER_HOST, SERVER_PORT, CLIENT_TIMEOUT);
            TProtocolFactory protocol = new TBinaryProtocol.Factory();
            client = new UserService.AsyncClient(protocol, clientManager, transport);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 3; i++) {
            final int cur = i;
            try {
                client.ping("测试 " + cur, new AsyncMethodCallback() {
                    @Override
                    public void onComplete(Object response) {
                        logger.debug("ping {} : {}", cur, response);
                    }

                    @Override
                    public void onError(Exception exception) {
                        logger.error("ping {} error.", cur, exception);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("nonblocking end");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void myPoolFactory() {
        ThriftTemplate thriftTemplate = new ThriftTemplate(SERVER_HOST, SERVER_PORT);
        for (int i = 1; i <= 7; i++) {
            final int cur = i;

            new Thread(() -> thriftTemplate.execute(client -> {
                try {
                    logger.debug("ping {} {}", cur, client.ping("测试" + cur));
                } catch (TException e) {
                    logger.error("ping cur:{} error.", cur, e);
                }
            })).start();

//            new Thread(() -> {
//                String result = thriftTemplate.execute(client -> {
//                    try {
//                        return client.ping("测试" + cur);
//                    } catch (TException e) {
//                        logger.error("ping cur:{} error.", cur, e);
//                    }
//                    return null;
//                });
//                logger.debug("ping {} {}", cur, result);
//            }).start();

        }
    }

}
