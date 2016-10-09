package com.aidar.repository;

import com.aidar.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by paradise on 30.04.16.
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
