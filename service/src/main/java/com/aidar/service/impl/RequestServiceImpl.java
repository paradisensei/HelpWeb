package com.aidar.service.impl;

import com.aidar.enums.RequestStatus;
import com.aidar.model.Request;
import com.aidar.model.User;
import com.aidar.repository.RequestRepository;
import com.aidar.repository.UserRepository;
import com.aidar.service.GoogleMapsService;
import com.aidar.service.RequestService;
import com.aidar.service.SecurityService;
import com.aidar.util.DateUtil;
import com.aidar.util.google_api.LocationBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by paradise on 21.04.16.
 */
@Service
@Transactional
public class RequestServiceImpl implements RequestService {

    @Autowired
    private GoogleMapsService googleMapsService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> getRecent() {
        return requestRepository.findAllByCreatedAtBetween(DateUtil.getMonthAgoDate(), new Date());
    }

    @Override
    public List<Request> getMy() {
        User user = securityService.getPersistedPrincipal();
        return requestRepository.findAllByNeedyOrVolunteer(user, user);
    }

    @Override
    public List<Request> getPending() {
        List<Request> pending = requestRepository.findAllByStatus(RequestStatus.PENDING);
        pending.removeAll(requestRepository
                .findAllByNeedy(securityService.getPersistedPrincipal()));
        return pending;
    }

    @Override
    public List<Request> getClosedAsVolunteer(Long id) {
        User user = userRepository.findOne(id);
        return requestRepository.findAllByVolunteerAndStatus(user, RequestStatus.CLOSED);
    }

    @Override
    public List<Request> getClosedAsNeedy(Long id) {
        User user = userRepository.findOne(id);
        return requestRepository.findAllByNeedyAndStatus(user, RequestStatus.CLOSED);
    }

    @Override
    public Request getOne(Long id) {
        return requestRepository.findOne(id);
    }

    @Override
    public void add(Request request) {
        LocationBody location = googleMapsService.getLocation(request.getAddress());
        request.setLatitude(location.getLatitude());
        request.setLongitude(location.getLongitude());
        request.setNeedy(securityService.getPersistedPrincipal());
        request.setCreatedAt(new Date());
        request.setStatus(RequestStatus.PENDING);
        requestRepository.save(request);
    }

    @Override
    public void help(Long id) {
        Request request = requestRepository.findOne(id);
        if (request.getVolunteer() == null) {
            request.setVolunteer(securityService.getPersistedPrincipal());
            request.setStatus(RequestStatus.ACTIVE);
        }
    }

    @Override
    public void close(Long id) {
        Request request = requestRepository.findOne(id);
        request.setStatus(RequestStatus.CLOSED);
    }

}
