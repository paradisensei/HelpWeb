package com.aidar.model;

import com.aidar.enums.AssessmentType;

import javax.persistence.*;

/**
 * Created by paradise on 29.04.16.
 */
@Entity
@Table(name = "assessment")
@SequenceGenerator(name = "assessment_gen",
        sequenceName = "assessment_seq", allocationSize = 1)
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assessment_gen")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "estimator_id")
    private User estimator;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "estimated_id")
    private User estimated;

    @Column(name = "assessment_type")
    @Enumerated(EnumType.STRING)
    private AssessmentType assessmentType;

    public Assessment() {
    }

    public Assessment(User estimator, User estimated) {
        this.estimator = estimator;
        this.estimated = estimated;
    }

    public Assessment(User estimator, User estimated, AssessmentType assessmentType) {
        this.estimator = estimator;
        this.estimated = estimated;
        this.assessmentType = assessmentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getEstimator() {
        return estimator;
    }

    public void setEstimator(User estimator) {
        this.estimator = estimator;
    }

    public User getEstimated() {
        return estimated;
    }

    public void setEstimated(User estimated) {
        this.estimated = estimated;
    }

    public AssessmentType getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(AssessmentType assessmentType) {
        this.assessmentType = assessmentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assessment)) return false;

        Assessment that = (Assessment) o;

        if (!getId().equals(that.getId())) return false;
        if (!getEstimator().equals(that.getEstimator())) return false;
        if (!getEstimated().equals(that.getEstimated())) return false;
        return getAssessmentType() == that.getAssessmentType();

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getEstimator().hashCode();
        result = 31 * result + getEstimated().hashCode();
        result = 31 * result + getAssessmentType().hashCode();
        return result;
    }
}
