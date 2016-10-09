package com.aidar.service.impl;

import com.aidar.config.NewsServiceTestConfig;
import com.aidar.model.Community;
import com.aidar.model.News;
import com.aidar.model.User;
import com.aidar.repository.CommunityRepository;
import com.aidar.repository.NewsRepository;
import com.aidar.service.NewsService;
import com.aidar.service.SecurityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by paradise on 07.05.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NewsServiceTestConfig.class,
        loader = AnnotationConfigContextLoader.class)
public class NewsServiceTest {

    @Autowired
    private NewsService newsService;

    // Mocked dependencies

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Test
    public void addShouldWorkCorrect() {
        String text = "Some text";
        User user = new User();
        user.setId(1);
        Community community = new Community();
        community.setId(1);
        News news = new News(text, community, user);
        when(communityRepository.findOne(community.getId())).thenReturn(community);
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        newsService.add(community.getId(), text);
        verify(newsRepository, times(1)).save(news);
    }

}
