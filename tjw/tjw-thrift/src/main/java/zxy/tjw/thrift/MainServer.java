package zxy.tjw.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zxy.tjw.thrift.thrift.UserService;
import zxy.tjw.thrift.thriftimpl.UserServiceImpl;

public class MainServer {
    private static final Logger logger = LoggerFactory.getLogger(MainServer.class);
    public static final int PORT = 9696;
    private static TProcessor processor;

    public static void main(String[] args) {
        logger.info("start");
        processor = new UserService.Processor(new UserServiceImpl());
        try {
            simple(null);
//            simple(new TFramedTransport.Factory());
//            threadPool(null);
//            threadPool(new TFramedTransport.Factory());
//            nonblocking();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        logger.info("end");
    }

    private static void simple(TFramedTransport.Factory transportFactory) {
        try {
            TServerTransport serverTransport = new TServerSocket(PORT);
            TServer.Args args = new TServer.Args(serverTransport);
            if (transportFactory != null) {
                args.transportFactory(transportFactory);
            }
            args.processor(processor);
            TServer server = new TSimpleServer(args);
            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void threadPool(TFramedTransport.Factory transportFactory) {
        try {
            TServerTransport serverTransport = new TServerSocket(PORT);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
            if (transportFactory != null) {
                args.transportFactory(transportFactory);
            }
            args.processor(processor);
            TServer server = new TThreadPoolServer(args);
            System.out.println("Starting the threadPool server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void nonblocking() {
        try {
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(PORT);
            TNonblockingServer.Args args = new TNonblockingServer.Args(serverTransport);
            args.processor(processor);
            TServer server = new TNonblockingServer(args);
            System.out.println("Starting the Non blocking server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
