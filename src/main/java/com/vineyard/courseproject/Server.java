package com.vineyard.courseproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//@EnableJpaRepositories(basePackages = "com.vineyard.courseproject.repositories")
//@EntityScan("com.training.springboot.springboottrainingapp.domain")
//@EnableRedisHttpSession
@SpringBootApplication
public class Server {
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
