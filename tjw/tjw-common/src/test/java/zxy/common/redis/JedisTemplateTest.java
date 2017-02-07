package zxy.common.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Jasper.Zhong
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-redis.xml" })
public class JedisTemplateTest {
    @Autowired
    private JedisTemplate jedisTemplate;

    private static final String TEST_KEY_GET = "test_key_get";

    @Test
    public void get() {
        System.out.println(jedisTemplate.get(TEST_KEY_GET));
    }

    @Test
    public void set() {
        jedisTemplate.set(TEST_KEY_GET, System.currentTimeMillis() + "");
    }
}
