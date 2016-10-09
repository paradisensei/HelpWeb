package com.aidar.service.impl;

import com.aidar.config.RequestServiceTestConfig;
import com.aidar.enums.RequestStatus;
import com.aidar.model.Request;
import com.aidar.model.User;
import com.aidar.repository.RequestRepository;
import com.aidar.repository.UserRepository;
import com.aidar.service.GoogleMapsService;
import com.aidar.service.RequestService;
import com.aidar.service.SecurityService;
import com.aidar.util.google_api.LocationBody;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by paradise on 06.05.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RequestServiceTestConfig.class,
        loader = AnnotationConfigContextLoader.class)
public class RequestServiceTest {

    @Autowired
    private RequestService requestService;

    // Mocked dependencies

    @Autowired
    private SecurityService securityService;

    @Autowired
    private GoogleMapsService googleMapsService;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    // Test data

    @Autowired
    private Request request;

    private static List<Request> requests;
    private static User user;

    @BeforeClass
    public static void setup() {
        requests = new ArrayList<>();
        Request request = new Request();
        request.setId(1);
        requests.add(request);

        user = new User();
        user.setId(1);
    }

    @Test
    public void getAllShouldReturnAllRequests() {
        when(requestRepository.findAll()).thenReturn(requests);
        assertEquals(requests, requestService.getAll());
    }

    @Test
    public void getRecentShouldReturnAllRequestsIssuedAtSomePeriodOfTime() {
        when(requestRepository.findAllByCreatedAtBetween(any(Date.class), any(Date.class)))
                .thenReturn(requests);
        assertEquals(requests, requestService.getRecent());
    }

    @Test
    public void getMyShouldReturnCurrentPrincipalRequests() {
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        when(requestRepository.findAllByNeedyOrVolunteer(user, user)).thenReturn(requests);
        List<Request> result = requestService.getMy();

        verify(securityService, atLeastOnce()).getPersistedPrincipal();
        assertEquals(requests, result);
    }

    @Test
    public void getPendingShouldReturnOtherUsersPendingRequests() {
        when(requestRepository.findAllByStatus(RequestStatus.PENDING)).thenReturn(requests);
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        when(requestRepository.findAllByNeedy(user)).thenReturn(requests);

        List<Request> expected = new ArrayList<>();
        assertEquals(expected, requestService.getPending());
    }

    @Test
    public void getClosedAsVolunteerShouldReturnAllClosedRequestsOfParticularVolunteer() {
        when(userRepository.findOne(user.getId())).thenReturn(user);
        when(requestRepository.findAllByVolunteerAndStatus(user, RequestStatus.CLOSED))
                .thenReturn(requests);
        assertEquals(requests, requestService.getClosedAsVolunteer(user.getId()));
    }

    @Test
    public void getClosedAsNeedyShouldReturnAllClosedRequestsOfParticularNeedy() {
        when(userRepository.findOne(user.getId())).thenReturn(user);
        when(requestRepository.findAllByNeedyAndStatus(user, RequestStatus.CLOSED))
                .thenReturn(requests);
        assertEquals(requests, requestService.getClosedAsNeedy(user.getId()));
    }

    @Test
    public void getOneShouldReturnOneRequestWithSpecifiedId() {
        Request request = new Request();
        request.setId(1);
        when(requestRepository.findOne(request.getId())).thenReturn(request);
        assertEquals(request, requestService.getOne(request.getId()));
    }

    @Test
    public void addShouldWorkCorrect() {
        String address = "Some address";
        double latitude = 50.01;
        double longitude = 40;
        Request request = mock(Request.class);
        when(request.getAddress()).thenReturn(address);
        LocationBody location = mock(LocationBody.class);
        when(location.getLatitude()).thenReturn(latitude);
        when(location.getLongitude()).thenReturn(longitude);
        when(googleMapsService.getLocation(address)).thenReturn(location);
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        requestService.add(request);

        verify(request, times(1)).setLatitude(location.getLatitude());
        verify(request, times(1)).setLongitude(location.getLongitude());
        verify(request, times(1)).setNeedy(user);
        verify(request, times(1)).setCreatedAt(any(Date.class));
        verify(request, times(1)).setStatus(RequestStatus.PENDING);
        verify(requestRepository, times(1)).save(request);
    }

    @Test
    public void helpShouldWorkCorrectIfRequestAlreadyHasAVolunteer() {
        when(request.getVolunteer()).thenReturn(user);
        when(requestRepository.findOne(request.getId())).thenReturn(request);
        requestService.help(request.getId());

        verify(request, atLeastOnce()).getVolunteer();
        verify(request, never()).setVolunteer(any(User.class));
        verify(request, never()).setStatus(RequestStatus.ACTIVE);
    }

    @Test
    public void helpShouldWorkCorrectIfRequestDoesNotHaveAVolunteerYet() {
        when(request.getVolunteer()).thenReturn(null);
        when(requestRepository.findOne(request.getId())).thenReturn(request);
        when(securityService.getPersistedPrincipal()).thenReturn(user);
        requestService.help(request.getId());

        verify(request, atLeastOnce()).getVolunteer();
        verify(request, times(1)).setVolunteer(user);
        verify(request, times(1)).setStatus(RequestStatus.ACTIVE);
    }

    @Test
    public void closeShouldWorkCorrect() {
        when(requestRepository.findOne(request.getId())).thenReturn(request);
        requestService.close(request.getId());
        verify(request, times(1)).setStatus(RequestStatus.CLOSED);
    }

}
