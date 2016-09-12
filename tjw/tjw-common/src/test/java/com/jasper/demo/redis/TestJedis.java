package com.jasper.demo.redis;

import redis.clients.jedis.Jedis;

/**
 * @author Jasper.Zhong
 */
public class TestJedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1");
        System.out.println(jedis.info());
    }
}
