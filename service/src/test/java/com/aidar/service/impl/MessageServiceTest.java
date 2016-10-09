package com.aidar.service.impl;

import com.aidar.config.MessageServiceTestConfig;
import com.aidar.enums.MessageStatus;
import com.aidar.model.Message;
import com.aidar.model.User;
import com.aidar.repository.MessageRepository;
import com.aidar.repository.UserRepository;
import com.aidar.service.MessageService;
import com.aidar.service.SecurityService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by paradise on 07.05.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MessageServiceTestConfig.class,
        loader = AnnotationConfigContextLoader.class)
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    // Mocked dependencies

    @Autowired
    private SecurityService securityService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    // Test data

    private static User user;
    private static User friend;
    private static List<Message> messages;

    @BeforeClass
    public static void setup() {
        user = new User();
        user.setId(1);

        friend = new User();
        friend.setId(2);

        messages = new ArrayList<>();
        Message message = new Message();
        message.setId(1);
        message.setText("Some text");
        message.setSender(friend);
        message.setRecipient(user);
        message.setStatus(MessageStatus.NEW);
        messages.add(message);
    }

    @Test
    public void getMyPenFriendsShouldReturnUsersThatHaveDialogsWithCurrentPrincipal() {
        Set<User> expected = new HashSet<>();
        expected.add(friend);
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        when(messageRepository.findAllBySenderOrRecipient(user, user)).thenReturn(messages);
        assertEquals(expected, messageService.getMyPenFriends());
    }

    @Test
    public void getDialogShouldReturnAllMessagesBetweenCurrentPrincipalAndUserWithSpecifiedId() {
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        when(userRepository.findOne(friend.getId())).thenReturn(friend);
        when(messageRepository.getDialog(user, friend)).thenReturn(messages);
        List<Message> result = messageService.getDialog(friend.getId());

        assertEquals(messages, result);
        result = result.stream()
                .filter(m -> m.getStatus() == MessageStatus.NEW)
                .collect(Collectors.toCollection(ArrayList::new));
        assertTrue(result.size() == 0);
    }

    @Test
    public void getNewShouldReturnAllNewMessagesSentToCurrentPrincipalBySpecifiedUser() {
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        when(userRepository.findOne(friend.getId())).thenReturn(friend);
        when(messageRepository.findAllBySenderAndRecipientAndStatus(friend, user, MessageStatus.NEW)).thenReturn(messages);
        List<Message> result = messageService.getNew(friend.getId());

        assertEquals(messages, result);
        result = result.stream()
                .filter(m -> m.getStatus() == MessageStatus.NEW)
                .collect(Collectors.toCollection(ArrayList::new));
        assertTrue(result.size() == 0);
    }

    @Test
    public void addShouldWorkCorrect() {
        String text = "Some text";
        Message message = new Message(text, user, friend);
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        when(userRepository.findOne(friend.getId())).thenReturn(friend);
        messageService.add(friend.getId(), text);
        verify(messageRepository, times(1)).save(message);
    }

}
