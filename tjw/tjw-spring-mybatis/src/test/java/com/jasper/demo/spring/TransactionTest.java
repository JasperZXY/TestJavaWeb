package com.jasper.demo.spring;

import com.jasper.demo.spring.mybatis.transaction.UserTransactionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 事务测试
 *
 * 不受检异常（运行时异常），数据库会回滚；受检异常，数据库不会回滚。
 * 调用其他类的声明了事务的方法，并在当前方法捕获异常，可以回滚。
 */
@ContextConfiguration(locations = { "classpath:appContext.xml" })
public class TransactionTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private UserTransactionService userTransactionService;

    @Test
    public void unCheckedException() {
        userTransactionService.unCheckedException();
    }

    @Test
    public void checkedException() throws Exception {
        userTransactionService.checkedException();
    }

    @Test
    public void innerMethod() {
        userTransactionService.innerMethod();
    }

    @Test
    public void otherClassMethod() {
        userTransactionService.otherClassMethod();
    }

    @Test
    public void currentClassMethod() {
        userTransactionService.currentClassMethod();
    }

    @Test
    public void timeout() {
        userTransactionService.timeout();
    }

}
