package zxy.commons;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

public class LockDelegate<T> {
    private volatile Map<Integer, Object> lockMap;
    private int maxSize = 1000;

    public LockDelegate() {
        lockMap = new HashedMap(maxSize);
    }

    public Object getLockObject(T key) {
        Integer lockKey = getLockKey(key);
        Object lockObj = lockMap.get(lockKey);
        if (lockObj == null) {
            synchronized (lockMap) {
                lockObj = lockMap.get(key);
                if (lockObj == null) {
                    lockObj = new Object();
                    lockMap.put(lockKey, lockObj);
                }
            }
        }
        return lockObj;
    }

    private synchronized Integer getLockKey(T key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode() % maxSize;
    }

}
