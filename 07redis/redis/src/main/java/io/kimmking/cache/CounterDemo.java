package io.kimmking.cache;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

public class CounterDemo {

    public static void main(String[] args) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(new JedisConnectionFactory());
        long counter = redisTemplate.opsForValue().increment("my_counter",1);
        long total = 100;
        System.out.println("已扣减："+counter);
        System.out.println("剩余："+(total - counter));
    }

}
