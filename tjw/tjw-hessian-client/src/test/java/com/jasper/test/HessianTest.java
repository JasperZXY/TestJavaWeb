package com.jasper.test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.jasper.client.service.MathServiceClient;
import com.jasper.client.service.MathServiceClient2;
import com.jasper.client.service.MathServiceClient3;

/**
 * 测试结果
 * 接口的包名跟类名不需要跟父类的一致，如果方法有重载的，需要setOverloadEnabled(true)
 * @author Jasper.Zhong
 */
public class HessianTest {
    public static void main(String[] args) throws Exception {
        String url = "http://127.0.0.1:8090/tjw-hessian-server/hessian/mathService";
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        MathServiceClient mathService = (MathServiceClient) hessianProxyFactory.create(MathServiceClient.class, url);
        System.out.println(mathService.add(1, 2));
        System.out.println("=====");

        try {
            MathServiceClient2 mathServiceClient2 = (MathServiceClient2) hessianProxyFactory.create(MathServiceClient2.class, url);
            System.out.println(mathServiceClient2.add(1, 3));
            // 会报错，com.caucho.hessian.client.HessianRuntimeException: com.caucho.hessian.io.HessianProtocolException: '￿' is an unknown code
            System.out.println(mathServiceClient2.add(1, 3, 2));
            System.out.println("=====");
        } catch (Exception e) {
            e.printStackTrace();
        }

        hessianProxyFactory.setOverloadEnabled(true);
        MathServiceClient3 mathServiceClient3 = (MathServiceClient3) hessianProxyFactory.create(MathServiceClient3.class, url);
        System.out.println(mathServiceClient3.add(1, 3));
        System.out.println(mathServiceClient3.add(1, 3, 4));
    }
}
