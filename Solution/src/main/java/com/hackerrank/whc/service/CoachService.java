package com.hackerrank.whc.service;

import com.hackerrank.whc.model.Coach;

import java.util.List;

public interface CoachService {

    public Coach createCoach(Coach coach);
    public List<Coach> getAllCoach();
    public Coach getById(int id);

}
