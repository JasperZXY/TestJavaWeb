package zxy.tjw.thrift.client;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author Jasper (zhongxianyao)
 */
public class TransportPoolFactory implements PoolableObjectFactory<TTransport> {
    private String serverHost;
    private int serverPort;

    public TransportPoolFactory(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    @Override
    public TTransport makeObject() throws Exception {
        TTransport transport = new TFramedTransport(new TSocket(serverHost, serverPort));
        transport.open();
        return transport;
    }

    @Override
    public void destroyObject(TTransport obj) throws Exception {
        obj.close();
    }

    @Override
    public boolean validateObject(TTransport obj) {
        return obj.isOpen();
    }

    @Override
    public void activateObject(TTransport obj) throws Exception {

    }

    @Override
    public void passivateObject(TTransport obj) throws Exception {

    }
}
