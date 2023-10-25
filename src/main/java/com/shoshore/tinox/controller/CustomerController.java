package com.shoshore.tinox.controller;

import com.shoshore.tinox.exception.TinoxException;
import com.shoshore.tinox.model.CustomerRequest;
import com.shoshore.tinox.repository.CustomerRepository;
import com.shoshore.tinox.service.customer.CustomerService;
import com.shoshore.tinox.util.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 25/10/2023, Wednesday
 **/
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private final CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;

    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }
    //create Customer
    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public CustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.createCustomer(customerRequest);
    }

    //update a Customer
    @PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public CustomerResponse updateCustomerById(
            @RequestBody CustomerRequest customerRequest) throws InvocationTargetException, IllegalAccessException {
        return customerService.updateCustomerById(customerRequest);
    }

    //get insurance  Customer
    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public CustomerResponse getCustomerById(@PathVariable("id") long id) {
        return customerService.getCustomerById(id);

    }

}
