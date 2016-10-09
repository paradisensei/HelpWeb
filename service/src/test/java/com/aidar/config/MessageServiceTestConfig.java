package com.aidar.config;

import com.aidar.repository.MessageRepository;
import com.aidar.repository.UserRepository;
import com.aidar.service.MessageService;
import com.aidar.service.SecurityService;
import com.aidar.service.impl.MessageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

/**
 * Created by paradise on 07.05.16.
 */
@Configuration
public class MessageServiceTestConfig {

    @Bean
    public MessageService messageService() {
        return new MessageServiceImpl();
    }

    @Bean
    public SecurityService securityService() {
        return mock(SecurityService.class);
    }

    @Bean
    public MessageRepository messageRepository() {
        return mock(MessageRepository.class);
    }

    @Bean
    public UserRepository userRepository() {
        return mock(UserRepository.class);
    }

}
