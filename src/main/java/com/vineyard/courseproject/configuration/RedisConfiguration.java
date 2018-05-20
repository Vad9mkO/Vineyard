package com.vineyard.courseproject.configuration;

import com.vineyard.courseproject.domain.UserSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpSession;

//@Import({LocalRedisConfiguration.class})
//@EnableRedisHttpSession
@ComponentScan("com.vineyard.courseproject")

@Configuration
public class RedisConfiguration {

    @Autowired //JedisPool
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    @Bean
    public Jackson2JsonRedisSerializer<UserSession> jackson2JsonRedisSerializer() {
        return new Jackson2JsonRedisSerializer<>(UserSession.class);
    }

//    @Bean
//    public RedisTemplate<String, UserSession>  redisTemplate() {
//        RedisTemplate<String, UserSession> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory);
//        redisTemplate.setKeySerializer(stringRedisSerializer());
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
////        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
////        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
////        redisTemplate.setKeySerializer(new StringRedisSerializer());
////        redisTemplate.setValueSerializer(new StringRedisSerializer());
////        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
//        return redisTemplate;
//    }
}


