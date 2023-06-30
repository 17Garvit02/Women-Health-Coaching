package com.hackerrank.whc.controller;

import com.hackerrank.whc.model.Customer;
import com.hackerrank.whc.repository.CustomerRepository;
import com.hackerrank.whc.service.CustomerService;
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
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private CustomerService service;

    //1. POST
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseEntity<Customer> addRecord(@RequestBody Customer customer) {
        ResponseEntity<Customer> res;
        try {
            res = new ResponseEntity<>(service.createCustomer(customer), HttpStatus.CREATED);
        }catch (DataIntegrityViolationException e) {
            res = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    //2. GET
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getRecords() {
        List<Customer> data = customerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    //4. GET by Id
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity getRecordsById(@PathVariable Integer id) {
        Optional<Customer> data = customerRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
