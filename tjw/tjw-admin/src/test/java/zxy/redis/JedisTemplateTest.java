package zxy.redis;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class JedisTemplateTest {
    @Autowired
    private JedisTemplate jedisTemplate;

    @Test
    public void get() {
        String key = "test";

        jedisTemplate.set(key, "1");
        System.out.println(jedisTemplate.get(key));

        jedisTemplate.set(key, "2");
        System.out.println(jedisTemplate.get(key));
    }
}
