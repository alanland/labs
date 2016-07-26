package hello.web

import hello.RedisService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory

/**
 * @author 王成义
 * @version 7/21/16
 */

public class RedisHttpSessionConfig {

    @Autowired
    RedisService redisService

    @Bean
    public JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }
}
