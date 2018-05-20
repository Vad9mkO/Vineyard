package com.vineyard.courseproject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
//import org.springframework.data.redis.connection.RedisPassword;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Optional;

@Configuration
//@Profile("dev") //@Profile(Constants.SPRING_PROFILE_PRODUCTION) or "default"
public class LocalRedisConfiguration {

    @Bean //JedisConnectionFactory //JedisPool
    public JedisConnectionFactory jedisConnectionFactory() {

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();

        try {
            //REDISTOGO_URL +
            //REDIS_URL +
            //REDISCLOUD_URL
            Optional<String> redisUrl = Optional.ofNullable(System.getenv("REDIS_URL"));

            if (redisUrl.isPresent()) {
                URI redisUri = new URI(redisUrl.get());

                redisStandaloneConfiguration.setHostName(redisUri.getHost());//localhost
                redisStandaloneConfiguration.setPort(redisUri.getPort());//6379
//                redisStandaloneConfiguration.setDatabase(0);
                redisStandaloneConfiguration.setPassword(RedisPassword.of(redisUri.getUserInfo().split(":", 2)[1]));
            } else {
                redisStandaloneConfiguration.setHostName("localhost");//localhost
                redisStandaloneConfiguration.setPort(6379);//6379
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofSeconds(Protocol.DEFAULT_TIMEOUT));// 60s connection timeout

        return jedisConnectionFactory;

        ////REDISTOGO

//        try {
//            String env = System.getenv("REDISTOGO_URL");
//            if(env != null) {
//                URI redisURI = new URI(env);
//
//                return new JedisPool(new JedisPoolConfig(),
//                        redisURI.getHost(),
//                        redisURI.getPort(),
//                        Protocol.DEFAULT_TIMEOUT,
//                        redisURI.getUserInfo().split(":", 2)[1]);
//            }
//            return new JedisPool();
//        } catch (URISyntaxException e) {
//            throw new RuntimeException("Redis couldn't be configured from URL in REDISTOGO_URL env var:"+
//                    System.getenv("REDISTOGO_URL"));
//        }

        ////Heroku redis

//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxTotal(10);
//        poolConfig.setMaxIdle(5);
//        poolConfig.setMinIdle(1);
//        poolConfig.setTestOnBorrow(true);
//        poolConfig.setTestOnCreate(true);
//        poolConfig.setTestWhileIdle(true);
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
//        return jedisConnectionFactory;

//// SPRING BOOT 1.5.13
//
////            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
////            jedisConnectionFactory.setUsePool(true);
////            jedisConnectionFactory.setHostName(redisUri.getHost());
////            jedisConnectionFactory.setPort(redisUri.getPort());
////            jedisConnectionFactory.setTimeout(60);
////             jedisConnectionFactory.setPassword(redisUri.getUserInfo().split(":",2)[1]);
////
////            return jedisConnectionFactory;
////
////            /** SPRING BOOT 2.0.2*/
////
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//            return null;
//        }
    }
}
