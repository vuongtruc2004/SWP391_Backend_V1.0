//package com.configuration.redis;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.listener.PatternTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
//
//@Configuration
//@RequiredArgsConstructor
//public class RedisExpirationEventContainer {
//
//    @Bean
//    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory, RedisExpirationEventListener listener) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(redisConnectionFactory);
//        container.addMessageListener(new MessageListenerAdapter(listener), new PatternTopic("__keyevent@0__:expired"));
//        return container;
//    }
//}
