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
        return new ResponseEntity<Customer>((Customer) null, HttpStatus.NOT_IMPLEMENTED);
    }

    //2. GET
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getRecords() {
        return new ResponseEntity<List<Customer>>((List<Customer>) null, HttpStatus.NOT_IMPLEMENTED);
    }

    //4. GET by Id
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getRecordsById(@PathVariable Integer id) {
        return new ResponseEntity<Customer>((Customer) null, HttpStatus.NOT_IMPLEMENTED);
    }
}
