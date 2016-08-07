package com.jasper.test;

import com.jasper.service.MathService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Jasper.Zhong
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/appContext.xml" })
@WebAppConfiguration
public class MathServiceTest {
    @Autowired
    private MathService mathService;

    @Test
    public void testAdd() {
        System.out.println(mathService.add(1,3));
    }
}
