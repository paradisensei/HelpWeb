package com.aidar.config;

import com.aidar.repository.CommunityRepository;
import com.aidar.repository.NewsRepository;
import com.aidar.service.NewsService;
import com.aidar.service.SecurityService;
import com.aidar.service.impl.NewsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

/**
 * Created by paradise on 07.05.16.
 */
@Configuration
public class NewsServiceTestConfig {

    @Bean
    public NewsService newsService() {
        return new NewsServiceImpl();
    }

    @Bean
    public SecurityService securityService() {
        return mock(SecurityService.class);
    }

    @Bean
    public NewsRepository newsRepository() {
        return mock(NewsRepository.class);
    }

    @Bean
    public CommunityRepository communityRepository() {
        return mock(CommunityRepository.class);
    }

}
