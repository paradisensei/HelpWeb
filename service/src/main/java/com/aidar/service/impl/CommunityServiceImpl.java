package com.aidar.service.impl;

import com.aidar.model.Community;
import com.aidar.model.User;
import com.aidar.model.UserCommunity;
import com.aidar.repository.CommunityRepository;
import com.aidar.repository.UserCommunityRepository;
import com.aidar.repository.UserRepository;
import com.aidar.service.CommunityService;
import com.aidar.service.SecurityService;
import com.aidar.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by paradise on 21.04.16.
 */
@Service
@Transactional
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCommunityRepository userCommunityRepository;

    @Override
    public List<Community> getAll() {
        return communityRepository.findAll();
    }

    @Override
    public List<Community> getRecent() {
        return communityRepository.findAllByCreatedAtBetween(DateUtil.getMonthAgoDate(), new Date());
    }

    private Set<Community> getSome(User user) {
        List<UserCommunity> communities = userCommunityRepository.findAllByUser(user);
        Set<Community> myCommunities = new HashSet<>();
        communities.forEach(c -> myCommunities.add(c.getCommunity()));
        return myCommunities;
    }

    @Override
    public Set<Community> getMy() {
        User principal = securityService.getPersistedPrincipal();
        return getSome(principal);
    }

    @Override
    public Set<Community> getByUser(Long id) {
        User user = userRepository.findOne(id);
        return getSome(user);
    }

    @Override
    public Community getOne(Long id) {
        return communityRepository.findOne(id);
    }

    @Override
    public boolean isMember(Long id) {
        User principal = securityService.getPersistedPrincipal();
        Community community = communityRepository.findOne(id);
        return userCommunityRepository.findOneByUserAndCommunity(principal, community) != null;
    }

    @Override
    public void add(Community community) {
        User principal = securityService.getPersistedPrincipal();
        community.setFounder(principal);
        community.setCreatedAt(new Date());
        communityRepository.save(community);
        userCommunityRepository.save(new UserCommunity(principal, community));
    }

    @Override
    public void addMember(Long id) {
        User principal = securityService.getPersistedPrincipal();
        Community community = communityRepository.findOne(id);
        if (userCommunityRepository.findOneByUserAndCommunity(principal, community) == null) {
            UserCommunity userCommunity = new UserCommunity(principal, community);
            userCommunityRepository.save(userCommunity);
        }
    }

    @Override
    public void removeMember(Long id) {
        User principal = securityService.getPersistedPrincipal();
        Community community = communityRepository.findOne(id);
        UserCommunity userCommunity = userCommunityRepository.findOneByUserAndCommunity(principal, community);
        userCommunityRepository.delete(userCommunity);
    }

}
