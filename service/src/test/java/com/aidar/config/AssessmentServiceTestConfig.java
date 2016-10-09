package com.aidar.config;

import com.aidar.repository.AssessmentRepository;
import com.aidar.repository.UserRepository;
import com.aidar.service.AssessmentService;
import com.aidar.service.SecurityService;
import com.aidar.service.impl.AssessmentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

/**
 * Created by paradise on 07.05.16.
 */
@Configuration
public class AssessmentServiceTestConfig {

    @Bean
    public AssessmentService assessmentService() {
        return new AssessmentServiceImpl();
    }

    @Bean
    public SecurityService securityService() {
        return mock(SecurityService.class);
    }

    @Bean
    public AssessmentRepository assessmentRepository() {
        return mock(AssessmentRepository.class);
    }

    @Bean
    public UserRepository userRepository() {
        return mock(UserRepository.class);
    }

}
