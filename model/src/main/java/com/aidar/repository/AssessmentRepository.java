package com.aidar.repository;

import com.aidar.model.Assessment;
import com.aidar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by paradise on 29.04.16.
 */
@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    List<Assessment> findAllByEstimated(User estimated);

    Assessment findOneByEstimatorAndEstimated(User estimator, User estimated);

}
