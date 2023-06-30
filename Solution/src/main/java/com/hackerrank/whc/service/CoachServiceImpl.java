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
        System.out.println(coach);
        if(coach.getName() == null || coach.getName().isBlank()) {
            throw new DataIntegrityViolationException("Name can not be null");
        } else {
            return coachRepository.save(coach);
        }
    }

    @Override
    public List<Coach> getAllCoach() {
        return coachRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Coach getById(int id) {
        return coachRepository.findById(id);
    }
}
