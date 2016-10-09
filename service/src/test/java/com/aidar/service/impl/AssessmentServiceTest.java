package com.aidar.service.impl;

import com.aidar.config.AssessmentServiceTestConfig;
import com.aidar.enums.AssessmentType;
import com.aidar.model.Assessment;
import com.aidar.model.User;
import com.aidar.repository.AssessmentRepository;
import com.aidar.repository.UserRepository;
import com.aidar.service.AssessmentService;
import com.aidar.service.SecurityService;
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
 * Created by paradise on 07.05.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AssessmentServiceTestConfig.class,
        loader = AnnotationConfigContextLoader.class)
public class AssessmentServiceTest {

    @Autowired
    private AssessmentService assessmentService;

    // Mocked dependencies

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private UserRepository userRepository;

    // Test data

    private static User estimated;
    private static User estimator;

    private static List<Assessment> assessments;

    @BeforeClass
    public static void setup() {
        estimated = new User();
        estimated.setId(1);

        estimator = new User();
        estimator.setId(2);

        assessments = new ArrayList<>();
        assessments.add(new Assessment(estimator, estimated, AssessmentType.LIKE));
        assessments.add(new Assessment(estimator, estimated, AssessmentType.LIKE));
        assessments.add(new Assessment(estimator, estimated, AssessmentType.DISLIKE));
    }

    @Test
    public void getMyRatingShouldReturnCurrentPrincipalRating() {
        when(securityService.getPersistedPrincipal()).thenReturn(estimated);
        when(assessmentRepository.findAllByEstimated(estimated)).thenReturn(assessments);
        assertEquals(1, assessmentService.getMyRating());
    }

    @Test
    public void getUserRatingShouldReturnRatingOfSpecifiedUser() {
        when(userRepository.findOne(estimated.getId())).thenReturn(estimated);
        when(assessmentRepository.findAllByEstimated(estimated)).thenReturn(assessments);
        assertEquals(1, assessmentService.getUserRating(estimated.getId()));
    }

    @Test
    public void getMyAssessmentOfUserShouldReturnCurrentPrincipalAssessmentOfSpecifiedUser() {
        Assessment assessment = new Assessment(estimator, estimated, AssessmentType.LIKE);
        when(securityService.getPersistedPrincipal()).thenReturn(estimator);
        when(userRepository.findOne(estimated.getId())).thenReturn(estimated);
        when(assessmentRepository.findOneByEstimatorAndEstimated(estimator, estimated))
                .thenReturn(assessment);
        assertEquals(assessment, assessmentService.getMyAssessmentOfUser(estimated.getId()));
    }

    @Test
    public void assessShouldWorkCorrect() {
        String assessmentType = "like";
        Assessment assessment = mock(Assessment.class);
        when(securityService.getPersistedPrincipal()).thenReturn(estimator);
        when(userRepository.findOne(estimated.getId())).thenReturn(estimated);
        when(assessmentRepository.findOneByEstimatorAndEstimated(estimator, estimated))
                .thenReturn(assessment);
        assessmentService.assess(estimated.getId(), assessmentType);

        verify(assessment, times(1))
                .setAssessmentType(AssessmentType.getAssessmentType(assessmentType));
        verify(assessmentRepository, times(1)).save(assessment);
    }

}
