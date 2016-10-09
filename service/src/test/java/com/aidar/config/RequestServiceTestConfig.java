package com.aidar.config;

import com.aidar.model.Request;
import com.aidar.repository.RequestRepository;
import com.aidar.repository.UserRepository;
import com.aidar.service.GoogleMapsService;
import com.aidar.service.RequestService;
import com.aidar.service.SecurityService;
import com.aidar.service.impl.RequestServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by paradise on 06.05.16.
 */
@Configuration
public class RequestServiceTestConfig {

    @Bean
    public RequestService requestService() {
        return new RequestServiceImpl();
    }

    @Bean
    public SecurityService securityService() {
        return mock(SecurityService.class);
    }

    @Bean
    public GoogleMapsService googleMapsService() {
        return mock(GoogleMapsService.class);
    }

    @Bean
    public RequestRepository requestRepository() {
        return mock(RequestRepository.class);
    }

    @Bean
    public UserRepository userRepository() {
        return mock(UserRepository.class);
    }

    @Bean
    public Request request() {
        Request request = mock(Request.class);
        when(request.getId()).thenReturn(1L);
        return request;
    }

}
