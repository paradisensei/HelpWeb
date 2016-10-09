package com.aidar.service.impl;

import com.aidar.config.UserServiceTestConfig;
import com.aidar.enums.Role;
import com.aidar.enums.UserStatus;
import com.aidar.model.User;
import com.aidar.repository.UserRepository;
import com.aidar.service.SecurityService;
import com.aidar.service.UserService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by paradise on 05.05.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserServiceTestConfig.class,
        loader = AnnotationConfigContextLoader.class)
public class UserServiceTest {

    // Mocked dependencies

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserRepository userRepository;

    // Test data

    private static User user;

    @BeforeClass
    public static void setup() {
        user = new User();
        user.setId(0);
    }

    @Test
    public void getAllShouldReturnAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findByRole(Role.ROLE_USER)).thenReturn(users);
        assertEquals(users, userService.getAll());
    }

    @Test
    public void getCurrentShouldReturnCurrentPrincipal() {
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        assertEquals(user, userService.getCurrent());
    }

    @Test
    public void getOneShouldReturnOneUserById() {
        when(userRepository.findOne(user.getId())).thenReturn(user);
        assertEquals(user, userService.getOne(user.getId()));
    }

    @Test
    public void addShouldWorkCorrect() {
        userService.add(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void updateShouldWorkCorrect() {
        User user = mock(User.class);
        User newUser = mock(User.class);
        when(userRepository.findOne(user.getId())).thenReturn(newUser);
        userService.update(user);

        verify(user, times(2)).getId();
        verify(userRepository, atLeastOnce()).findOne(user.getId());
        verify(newUser, times(1)).setEmail(user.getEmail());
        verify(newUser, times(1)).setName(user.getSurname());
        verify(newUser, times(1)).setSurname(user.getSurname());
    }

    @Test
    public void banShouldWorkCorrect() {
        String email = "user@gmail.com";
        User user = mock(User.class);
        when(user.getEmail()).thenReturn(email);
        when(userRepository.findOneByEmail(email)).thenReturn(user);
        userService.ban(email);

        verify(userRepository, atLeastOnce()).findOneByEmail(email);
        verify(user, times(1)).setStatus(UserStatus.BANNED);
    }

    @Test
    public void pardonShouldWorkCorrect() {
        String email = "user@gmail.com";
        User user = mock(User.class);
        when(user.getEmail()).thenReturn(email);
        when(userRepository.findOneByEmail(email)).thenReturn(user);
        userService.pardon(email);

        verify(userRepository, atLeastOnce()).findOneByEmail(email);
        verify(user, times(1)).setStatus(UserStatus.ACTIVE);
    }

}
