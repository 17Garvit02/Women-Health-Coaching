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
        return new ResponseEntity<Coach>((Coach) null, HttpStatus.NOT_IMPLEMENTED);
    }

    //2. GET
    @RequestMapping(value = "/coach", method = RequestMethod.GET)
    public ResponseEntity<List<Coach>> getRecords() {
        return new ResponseEntity<List<Coach>>((List<Coach>) null, HttpStatus.NOT_IMPLEMENTED);
    }

    //4. GET by Id
    @RequestMapping(value = "/coach/{id}", method = RequestMethod.GET)
    public ResponseEntity<Coach> getRecordsById(@PathVariable Integer id) {
        return new ResponseEntity<Coach>((Coach) null, HttpStatus.NOT_IMPLEMENTED);
    }
}
