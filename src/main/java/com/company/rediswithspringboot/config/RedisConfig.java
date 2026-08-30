package com.company.rediswithspringboot.config;

import com.company.rediswithspringboot.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory connectionFactory
    ) {

        RedisTemplate<String, Object> redisTemplate =
                new RedisTemplate<>();

        redisTemplate.setConnectionFactory(connectionFactory);

        // Key → String
        redisTemplate.setKeySerializer(
                new StringRedisSerializer()
        );

        // Value -> User JSON
        redisTemplate.setValueSerializer(
                new JacksonJsonRedisSerializer<>(User.class)
        );

        // Hash Key → String
        redisTemplate.setHashKeySerializer(
                new StringRedisSerializer()
        );

        // Hash value -> User JSON
        redisTemplate.setHashValueSerializer(
                new JacksonJsonRedisSerializer<>(User.class)
        );

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}