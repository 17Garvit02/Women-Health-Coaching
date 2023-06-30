package com.hackerrank.whc.service;

import com.hackerrank.whc.model.Coach;
import com.hackerrank.whc.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService{

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public Coach createCoach(Coach coach) {
        return null;
    }

    @Override
    public List<Coach> getAllCoach() {
        return null;
    }

    @Override
    public Coach getById(int id) {
        return null;
    }
}
