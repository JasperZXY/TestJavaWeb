package zxy.tjw.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zxy.tjw.thrift.thrift.UserService;

/**
 * @author Jasper (zhongxianyao)
 */
public class UserServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceClient.class);

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9696;

    public static void main(String[] args) {
//        simple();
        thread();
    }

    private static void simple() {
        try {
            TTransport transport = new TSocket(SERVER_HOST, SERVER_PORT);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            UserService.Client client = new UserService.Client(protocol);
            // 调用服务方法
            for (int i = 1 ; i <= 2 ; i++) {
                final int cur = i;
                logger.debug("ping {} : {}", cur, client.ping("测试 " + cur));
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
        for (int i = 1 ; i <= 3 ; i++) {
            final int cur = i;
            new Thread(() -> {
                try {
                    synchronized (lockObj) {
                        logger.debug("ping {} : {}", cur, client.ping("测试 " + cur));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println("thread end");
    }

}
