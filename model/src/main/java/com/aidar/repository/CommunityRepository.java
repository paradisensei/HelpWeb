package com.aidar.repository;

import com.aidar.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by paradise on 16.04.16.
 */
@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

    List<Community> findAllByCreatedAtBetween(Date start, Date end);

}
