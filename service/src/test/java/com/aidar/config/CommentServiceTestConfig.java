package com.aidar.config;

import com.aidar.repository.CommentRepository;
import com.aidar.repository.RequestRepository;
import com.aidar.service.CommentService;
import com.aidar.service.SecurityService;
import com.aidar.service.impl.CommentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

/**
 * Created by paradise on 07.05.16.
 */
@Configuration
public class CommentServiceTestConfig {

    @Bean
    public CommentService commentService() {
        return new CommentServiceImpl();
    }

    @Bean
    public SecurityService securityService() {
        return mock(SecurityService.class);
    }

    @Bean
    public CommentRepository commentRepository() {
        return mock(CommentRepository.class);
    }

    @Bean
    public RequestRepository requestRepository() {
        return mock(RequestRepository.class);
    }

}
