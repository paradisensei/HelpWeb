package com.aidar.service;

import com.aidar.model.Request;

import java.util.List;

/**
 * Created by paradise on 21.04.16.
 */
public interface RequestService {

    List<Request> getAll();

    List<Request> getRecent();

    List<Request> getMy();

    List<Request> getPending();

    List<Request> getClosedAsVolunteer(Long id);

    List<Request> getClosedAsNeedy(Long id);

    Request getOne(Long id);

    void add(Request request);

    void help(Long id);

    void close(Long id);

}
