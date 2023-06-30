package com.hackerrank.whc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.whc.model.Coach;
import com.hackerrank.whc.model.Customer;
import com.hackerrank.whc.repository.CoachRepository;
import com.hackerrank.whc.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ApplicationTests {
    ObjectMapper om = new ObjectMapper();
    @Autowired
    CoachRepository coachRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    MockMvc mockMvc;



    @Test
    public void createCoach_InvalidData() throws Exception {
        mockMvc.perform(post("/api/coach")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createCustomer_InvalidData() throws Exception {
        mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"height\":170,\"weight\":-10}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createCoach_MissingRequiredField() throws Exception {
        mockMvc.perform(post("/api/coach")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createCustomer_MissingRequiredField() throws Exception {
        mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"height\":170}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCoachById_InvalidId() throws Exception {
        mockMvc.perform(get("/api/coach/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getCustomerById_InvalidId() throws Exception {
        mockMvc.perform(get("/api/customer/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createCoach() throws Exception {
        Coach coach = new Coach("John Doe");
        mockMvc.perform(post("/api/coach")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(coach)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John Doe")));
    }

    @Test
    public void getAllCoaches() throws Exception {

        Coach coach = new Coach("John Doe");
        mockMvc.perform(post("/api/coach")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(coach)))
                .andDo(print())
                .andExpect(status().isCreated());
        coach = new Coach("Jane Smith");
        mockMvc.perform(post("/api/coach")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(coach)))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/coach"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Jane Smith")));
    }

    @Test
    public void getCoachById() throws Exception {
        Coach coach = new Coach("John Doe");
        mockMvc.perform(post("/api/coach")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(coach)))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/coach/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John Doe")));
    }

    @Test
    public void createCustomer() throws Exception {
        mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"height\":170,\"weight\":65}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.height", is(170)))
                .andExpect(jsonPath("$.weight", is(65)));
    }

    @Test
    public void getAllCustomers() throws Exception {
        Customer customer1 = new Customer();
        customer1.setHeight(170);
        customer1.setWeight(65);

        Customer customer2 = new Customer();
        customer2.setHeight(160);
        customer2.setWeight(55);

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        mockMvc.perform(get("/api/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].height", is(170)))
                .andExpect(jsonPath("$[0].weight", is(65)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].height", is(160)))
                .andExpect(jsonPath("$[1].weight", is(55)));
    }

    @Test
    public void getCustomerById() throws Exception {
        Customer customer1 = new Customer();
        customer1.setHeight(170);
        customer1.setWeight(65);
        customerRepository.save(customer1);
        mockMvc.perform(get("/api/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.height", is(170)))
                .andExpect(jsonPath("$.weight", is(65)));
    }
}