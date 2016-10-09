package com.aidar.service;

import com.aidar.model.Comment;

/**
 * Created by paradise on 27.04.16.
 */
public interface CommentService {

    Comment add(Long id, String text);

}
