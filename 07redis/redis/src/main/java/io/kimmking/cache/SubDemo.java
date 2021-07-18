package io.kimmking.cache;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

public class SubDemo {

    public static void main(String[] args) throws Exception {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(new JedisConnectionFactory());
        String topic = "mytopic";
        redisTemplate.getConnectionFactory().getConnection().subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] bytes) {
                System.out.println(new String(message.getBody()));
            }
        }, topic.getBytes());
    }
}
