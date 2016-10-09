package com.aidar.repository;

import com.aidar.enums.RequestStatus;
import com.aidar.model.Request;
import com.aidar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by paradise on 16.04.16.
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByNeedy(User needy);

    List<Request> findAllByNeedyOrVolunteer(User needy, User volunteer);

    List<Request> findAllByStatus(RequestStatus status);

    List<Request> findAllByVolunteerAndStatus(User volunteer, RequestStatus status);

    List<Request> findAllByNeedyAndStatus(User needy, RequestStatus status);

    List<Request> findAllByCreatedAtBetween(Date start, Date end);

}
