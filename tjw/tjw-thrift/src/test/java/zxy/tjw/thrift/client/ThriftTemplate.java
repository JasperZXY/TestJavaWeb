package zxy.tjw.thrift.client;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zxy.tjw.thrift.thrift.UserService;

/**
 * @author Jasper (zhongxianyao)
 */
public class ThriftTemplate {
    private static final Logger logger = LoggerFactory.getLogger(ThriftTemplate.class);

//    private String serverHost;
//    private int serverPort;
    private PoolableObjectFactory<TTransport> transportPoolFactory;
    private GenericObjectPool.Config config;
    private ObjectPool<TTransport> transportObjectPool;

    public ThriftTemplate(String serverHost, int serverPort) {
        PoolableObjectFactory<TTransport> transportPoolFactory = new TransportPoolFactory(serverHost, serverPort);
        config = new GenericObjectPool.Config();
        config.lifo = false;
        config.maxActive = 5;
        config.lifo = false;
        config.maxActive = 5;
        config.maxIdle = 4;
        config.minIdle = 1;
        config.maxWait = 5000;
//        config.maxWait = 1500;
        transportObjectPool = new GenericObjectPool<>(transportPoolFactory, config);
    }

    public interface ThriftActionNoResult {
        void action(UserService.Client client);
    }

    public interface ThriftAction<Result> {
        Result action(UserService.Client client);
    }

    public void execute(ThriftActionNoResult thriftActionNoResult) {
        TTransport transport = null;
        try {
            transport = transportObjectPool.borrowObject();
            TProtocol protocol = new TBinaryProtocol(transport);
            UserService.Client client = new UserService.Client(protocol);
            thriftActionNoResult.action(client);
        } catch (Exception e) {
            logger.debug("myPoolFactory error.", e);
            try {
                transportObjectPool.invalidateObject(transport);
            } catch (Exception e1) {
                logger.error("invalidateObject error.", e1);
            }
        } finally {
            try {
                transportObjectPool.returnObject(transport);
            } catch (Exception e) {
                logger.error("returnObject error.", e);
            }
        }
    }

    public <Result> Result execute(ThriftAction<Result> thriftAction) {
        TTransport transport = null;
        try {
            transport = transportObjectPool.borrowObject();
            TProtocol protocol = new TBinaryProtocol(transport);
            UserService.Client client = new UserService.Client(protocol);
            return thriftAction.action(client);
        } catch (Exception e) {
            logger.debug("myPoolFactory error.", e);
            try {
                transportObjectPool.invalidateObject(transport);
            } catch (Exception e1) {
                logger.error("invalidateObject error.", e1);
            }
        } finally {
            try {
                transportObjectPool.returnObject(transport);
            } catch (Exception e) {
                logger.error("returnObject error.", e);
            }
        }
        return null;
    }
}
