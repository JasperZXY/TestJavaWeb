package zxy;

import zxy.commons.LockDelegate;

public class LockDelegateTest {
    public static void main(String[] args) {
        LockDelegate<String> lockDelegate1 = new LockDelegate<>();
        LockDelegate<Double> lockDelegate2 = new LockDelegate<>();

        System.out.println(lockDelegate1.getLockObject("test1"));
        System.out.println(lockDelegate1.getLockObject("test1"));
        System.out.println(lockDelegate1.getLockObject("test2"));

        System.out.println(lockDelegate2.getLockObject(1.1));
        System.out.println(lockDelegate2.getLockObject(1.1));
        System.out.println(lockDelegate2.getLockObject(1.2));
    }
}
