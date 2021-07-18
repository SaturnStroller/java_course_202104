package io.kimmking.cache;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

public class PubDemo {

    public static void main(String[] args) throws Exception {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(new JedisConnectionFactory());
        String topic = "mytopic";
        for (int i = 0; i < 10; i++) {
            Thread.sleep(2000);
            String value = "msg"+i;
            System.out.println("value:"+value);
            redisTemplate.getConnectionFactory().getConnection().publish(topic.getBytes(),value.getBytes());
        }
    }

}
