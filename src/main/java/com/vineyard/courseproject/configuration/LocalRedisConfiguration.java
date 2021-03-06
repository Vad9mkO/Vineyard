package com.vineyard.courseproject.configuration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
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

//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//
//        try {
//            //REDISTOGO_URL +
//            //REDIS_URL +
//            //REDISCLOUD_URL
//            Optional<String> redisUrl = Optional.ofNullable(System.getenv("REDIS_URL"));
//
//            if (redisUrl.isPresent()) {
//                URI redisUri = new URI(redisUrl.get());
//
//                redisStandaloneConfiguration.setHostName(redisUri.getHost());//localhost
//                redisStandaloneConfiguration.setPort(redisUri.getPort());//6379
//                redisStandaloneConfiguration.setDatabase(0);
//                redisStandaloneConfiguration.setPassword(RedisPassword.of(redisUri.getUserInfo().split(":", 2)[1]));
//            } else {
//                redisStandaloneConfiguration.setHostName("localhost");//localhost
//                redisStandaloneConfiguration.setPort(6379);//6379
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //Try without JedisClientConfiguration !!!
//        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
//        jedisClientConfiguration.usePooling();
////        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
//        jedisClientConfiguration.connectTimeout(Duration.ofSeconds(Protocol.DEFAULT_TIMEOUT));// 60s connection timeout
//
//        return jedisConnectionFactory;

        // DEPRECATED BUT WORKING

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        try {
            //REDISTOGO_URL +
            //REDIS_URL +
            //REDISCLOUD_URL
            Optional<String> redisUrl = Optional.ofNullable(System.getenv("REDIS_URL"));

            if (redisUrl.isPresent()) {
                URI redisUri = new URI(redisUrl.get());

                jedisConnectionFactory.setUsePool(true);
                jedisConnectionFactory.setHostName(redisUri.getHost());
                jedisConnectionFactory.setPort(redisUri.getPort());
                jedisConnectionFactory.setTimeout(60);
                jedisConnectionFactory.setPassword(redisUri.getUserInfo().split(":",2)[1]);


            } else {
                jedisConnectionFactory.setHostName("localhost");//localhost
                jedisConnectionFactory.setPort(6379);//6379
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return jedisConnectionFactory;

        //WORKING BUT NEW

//        try {
//            Optional<String> env = Optional.ofNullable(System.getenv("REDISCLOUD_URL"));
//            if(env.isPresent()) {
//                URI redisURI = new URI(env.get());
//
//                return new JedisPool(new JedisPoolConfig(),
//                        redisURI.getHost(),
//                        redisURI.getPort(),
//                        Protocol.DEFAULT_TIMEOUT,
//                        redisURI.getUserInfo().split(":", 2)[1]);
//            }
//            return new JedisPool(new JedisPoolConfig(), "localhost", 6379, Protocol.DEFAULT_TIMEOUT);
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
////        poolConfig.setTestOnCreate(true);
////        poolConfig.setTestWhileIdle(true);
//        poolConfig.setTestOnReturn(true);
//
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
//        jedisConnectionFactory.setUsePool(true);
//        return jedisConnectionFactory;

    }
}
