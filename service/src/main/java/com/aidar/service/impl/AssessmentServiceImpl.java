package com.aidar.service.impl;

import com.aidar.enums.AssessmentType;
import com.aidar.model.Assessment;
import com.aidar.model.User;
import com.aidar.repository.AssessmentRepository;
import com.aidar.repository.UserRepository;
import com.aidar.service.AssessmentService;
import com.aidar.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by paradise on 29.04.16.
 */
@Service
@Transactional
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private UserRepository userRepository;

    private int getRating(User user) {
        List<Assessment> assessments = assessmentRepository.findAllByEstimated(user);
        int rating = 0;
        for (Assessment a : assessments) {
            switch (a.getAssessmentType()) {
                case LIKE:
                    rating++;
                    break;
                case DISLIKE:
                    rating--;
                    break;
            }
        }
        return rating;
    }

    @Override
    public int getMyRating() {
        User user = securityService.getPersistedPrincipal();
        return getRating(user);
    }

    @Override
    public int getUserRating(Long id) {
        User user = userRepository.findOne(id);
        return getRating(user);
    }

    @Override
    public Assessment getMyAssessmentOfUser(Long id) {
        User estimator = securityService.getPersistedPrincipal();
        User estimated = userRepository.findOne(id);
        return assessmentRepository
                .findOneByEstimatorAndEstimated(estimator, estimated);
    }

    @Override
    public void assess(Long id, String assessmentType) {
        User estimator = securityService.getPersistedPrincipal();
        User estimated = userRepository.findOne(id);
        Assessment assessment = assessmentRepository
                .findOneByEstimatorAndEstimated(estimator, estimated);
        if (assessment == null) {
            assessment = new Assessment(estimator, estimated);
        }
        assessment.setAssessmentType(AssessmentType.getAssessmentType(assessmentType));
        assessmentRepository.save(assessment);
    }

}
