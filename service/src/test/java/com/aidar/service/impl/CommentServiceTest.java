package com.aidar.service.impl;

import com.aidar.config.CommentServiceTestConfig;
import com.aidar.model.Comment;
import com.aidar.model.Request;
import com.aidar.model.User;
import com.aidar.repository.CommentRepository;
import com.aidar.repository.RequestRepository;
import com.aidar.service.CommentService;
import com.aidar.service.SecurityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.mockito.Mockito.*;

/**
 * Created by paradise on 07.05.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CommentServiceTestConfig.class,
        loader = AnnotationConfigContextLoader.class)
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    // Mocked dependencies

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Test
    public void addShouldWorkCorrect() {
        String text = "Some text";
        Request request = new Request();
        request.setId(1);
        User user = new User();
        user.setId(1);
        Comment comment = new Comment(text, request, user);
        when(requestRepository.findOne(request.getId())).thenReturn(request);
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        commentService.add(request.getId(), text);
        verify(commentRepository, times(1)).save(comment);
    }

}
