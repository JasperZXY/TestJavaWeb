package zxy.tjw.thrift.thriftimpl;

import org.apache.thrift.TException;
import zxy.tjw.thrift.thrift.UserService;

/**
 * @author Jasper (zhongxianyao)
 */
public class UserServiceImpl implements UserService.Iface {
    @Override
    public String ping(String name) throws TException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "pong:" + name;
    }
}
