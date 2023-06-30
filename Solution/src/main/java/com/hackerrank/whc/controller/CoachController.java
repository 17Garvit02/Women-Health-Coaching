package com.hackerrank.whc.controller;

import com.hackerrank.whc.model.Coach;
import com.hackerrank.whc.repository.CoachRepository;
import com.hackerrank.whc.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class CoachController {
    @Autowired
    private CoachService coachService;

    //1. POST
    @RequestMapping(value = "/coach", method = RequestMethod.POST)
    public ResponseEntity<Coach> addRecord(@RequestBody Coach coach) {
        ResponseEntity<Coach> res;
        try {
            res = new ResponseEntity<>(coachService.createCoach(coach), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            res = new ResponseEntity<>(coach, HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    //2. GET
    @RequestMapping(value = "/coach", method = RequestMethod.GET)
    public ResponseEntity<List<Coach>> getRecords() {
        return new ResponseEntity<>(coachService.getAllCoach(), HttpStatus.OK);
    }

    //4. GET by Id
    @RequestMapping(value = "/coach/{id}", method = RequestMethod.GET)
    public ResponseEntity<Coach> getRecordsById(@PathVariable Integer id) {
        Coach data = coachService.getById(id);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
