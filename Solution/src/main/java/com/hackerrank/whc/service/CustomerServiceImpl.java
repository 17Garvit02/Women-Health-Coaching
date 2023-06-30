package com.hackerrank.whc.service;

import com.hackerrank.whc.model.Customer;
import com.hackerrank.whc.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        if(customer.getHeight() == null || customer.getWeight() == null || customer.getWeight() <= 0 ||customer.getHeight() <= 0
        ) {
            throw new DataIntegrityViolationException("Required fields are missing");
        } else{
            return customerRepository.save(customer);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id);
    }
}
