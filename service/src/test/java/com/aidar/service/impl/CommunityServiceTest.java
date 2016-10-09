package com.aidar.service.impl;

import com.aidar.config.CommunityServiceTestConfig;
import com.aidar.model.Community;
import com.aidar.model.User;
import com.aidar.model.UserCommunity;
import com.aidar.repository.CommunityRepository;
import com.aidar.repository.UserCommunityRepository;
import com.aidar.repository.UserRepository;
import com.aidar.service.CommunityService;
import com.aidar.service.SecurityService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by paradise on 07.05.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CommunityServiceTestConfig.class,
        loader = AnnotationConfigContextLoader.class)
public class CommunityServiceTest {

    @Autowired
    private CommunityService communityService;

    // Mocked dependencies

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCommunityRepository userCommunityRepository;

    // Test data

    private static User user;
    private static Community community;
    private static UserCommunity userCommunity;

    private static List<Community> communities;
    private static List<UserCommunity> userCommunities;

    @BeforeClass
    public static void setup() {
        user = new User();
        user.setId(1);

        community = new Community();
        community.setId(1);

        userCommunity = new UserCommunity();
        userCommunity.setId(0);
        userCommunity.setUser(user);
        userCommunity.setCommunity(community);


        communities = new ArrayList<>();
        communities.add(community);

        userCommunities = new ArrayList<>();
        userCommunities.add(userCommunity);
    }

    @Test
    public void getAllShouldReturnAllCommunities() {
        when(communityRepository.findAll()).thenReturn(communities);
        assertEquals(communities, communityService.getAll());
    }

    @Test
    public void getRecentShouldReturnAllCommunitiesFoundedAtSomePeriodOfTime() {
        when(communityRepository.findAllByCreatedAtBetween(any(Date.class), any(Date.class)))
                .thenReturn(communities);
        assertEquals(communities, communityService.getRecent());
    }

    @Test
    public void getMyShouldReturnCurrentPrincipalCommunities() {
        Set<Community> communities = new HashSet<>();
        communities.add(community);
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        when(userCommunityRepository.findAllByUser(user)).thenReturn(userCommunities);
        Set<Community> result = communityService.getMy();

        verify(securityService, atLeastOnce()).getPersistedPrincipal();
        assertEquals(communities, result);
    }

    @Test
    public void getByUserShouldReturnSpecifiedUserCommunities() {
        Set<Community> communities = new HashSet<>();
        communities.add(community);
        when(userRepository.findOne(user.getId())).thenReturn(user);
        when(userCommunityRepository.findAllByUser(user)).thenReturn(userCommunities);
        Set<Community> result = communityService.getByUser(user.getId());

        verify(userRepository, atLeastOnce()).findOne(user.getId());
        assertEquals(communities, result);
    }

    @Test
    public void getOneShouldReturnOneCommunityWithSpecifiedId() {
        when(communityRepository.findOne(community.getId())).thenReturn(community);
        assertEquals(community, communityService.getOne(community.getId()));
    }

    @Test
    public void isMemberShouldAnswerWhetherCurrentPrincipalIsMemberOfSpecifiedCommunityOrNot() {
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        when(communityRepository.findOne(community.getId())).thenReturn(community);
        when(userCommunityRepository.findOneByUserAndCommunity(user, community))
                .thenReturn(userCommunity);
        assertTrue(communityService.isMember(community.getId()));
    }

    @Test
    public void addShouldWorkCorrect() {
        Community community = mock(Community.class);
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        communityService.add(community);

        verify(community, times(1)).setFounder(user);
        verify(community, times(1)).setCreatedAt(any(Date.class));
        verify(communityRepository, times(1)).save(community);
        verify(userCommunityRepository, times(1)).save(new UserCommunity(user, community));
    }

    @Test
    public void addMemberShouldWorkCorrectIfCurrentPrincipalIsAlreadyAMember() {
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        when(communityRepository.findOne(community.getId())).thenReturn(community);
        when(userCommunityRepository.findOneByUserAndCommunity(user, community))
                .thenReturn(userCommunity);
        communityService.addMember(community.getId());

        verify(userCommunityRepository, atLeastOnce()).findOneByUserAndCommunity(user, community);
        verify(userCommunityRepository, never()).save(userCommunity);
    }

//    @Test
//    public void addMemberShouldWorkCorrectIfCurrentPrincipalIsNotAMemberYet() {
//        when(securityService.getPersistedPrincipal()).thenReturn(user);
//        when(communityRepository.findOne(community.getId())).thenReturn(community);
//        when(userCommunityRepository.findOneByUserAndCommunity(user, community))
//                .thenReturn(null);
//        communityService.addMember(community.getId());
//
//        verify(userCommunityRepository, atLeastOnce()).findOneByUserAndCommunity(user, community);
//        verify(userCommunityRepository, times(1)).save(userCommunity);
//    }

    @Test
    public void removeMemberShouldWorkCorrect() {
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        when(communityRepository.findOne(community.getId())).thenReturn(community);
        when(userCommunityRepository.findOneByUserAndCommunity(user, community))
                .thenReturn(userCommunity);
        communityService.removeMember(community.getId());
        verify(userCommunityRepository, times(1)).delete(userCommunity);
    }

}
