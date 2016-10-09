package com.aidar.config;

import com.aidar.repository.CommunityRepository;
import com.aidar.repository.UserCommunityRepository;
import com.aidar.repository.UserRepository;
import com.aidar.service.CommunityService;
import com.aidar.service.SecurityService;
import com.aidar.service.impl.CommunityServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

/**
 * Created by paradise on 07.05.16.
 */
@Configuration
public class CommunityServiceTestConfig {

    @Bean
    public CommunityService communityService() {
        return new CommunityServiceImpl();
    }

    @Bean
    public SecurityService securityService() {
        return mock(SecurityService.class);
    }

    @Bean
    public CommunityRepository communityRepository() {
        return mock(CommunityRepository.class);
    }

    @Bean
    public UserRepository userRepository() {
        return mock(UserRepository.class);
    }

    @Bean
    public UserCommunityRepository userCommunityRepository() {
        return mock(UserCommunityRepository.class);
    }

}
