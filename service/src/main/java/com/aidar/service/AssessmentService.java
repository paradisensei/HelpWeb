package com.aidar.service;

import com.aidar.model.Assessment;

/**
 * Created by paradise on 29.04.16.
 */
public interface AssessmentService {

    int getMyRating();

    int getUserRating(Long id);

    Assessment getMyAssessmentOfUser(Long id);

    void assess(Long id, String assessment);

}
