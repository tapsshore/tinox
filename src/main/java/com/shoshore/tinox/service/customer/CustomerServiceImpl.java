package com.shoshore.tinox.service.customer;

import com.shoshore.tinox.entity.ContactInformation;
import com.shoshore.tinox.entity.Customer;
import com.shoshore.tinox.enums.CustomerStatus;
import com.shoshore.tinox.model.CustomerRequest;
import com.shoshore.tinox.repository.CustomerRepository;
import com.shoshore.tinox.util.AppConstants;
import com.shoshore.tinox.util.CustomerResponse;
import com.shoshore.tinox.util.RequestResponse;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 25/10/2023, Wednesday
 **/
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        CustomerResponse MISSING_FIELD = validateEmptyFields(customerRequest);
        if (MISSING_FIELD != null) return MISSING_FIELD;
        Customer customer = prepareCustomerRequest(customerRequest);
        customer.setDateCreated(LocalDate.now());
        customer = customerRepository.save(customer);
        return RequestResponse.getOKResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomerById(CustomerRequest customerRequest) throws InvocationTargetException, IllegalAccessException {
        // Check if Customer exists
        if (customerRequest.getId() == null) {
            return RequestResponse.getBADResponse(AppConstants.INVALID_CUSTOMER);
        }
        Optional<Customer> existingPolicyOptional = customerRepository.findCustomerById(customerRequest.getId());
        if (existingPolicyOptional.isEmpty()) {
            return RequestResponse.getBADResponse("Customer not found");
        }
        Customer existingPolicy = existingPolicyOptional.get();
        existingPolicy.setDateUpdated(LocalDate.now());
        updateNonNullFields(customerRequest, existingPolicy);

        Customer updatedCustomer = customerRepository.save(existingPolicy);
        return RequestResponse.getOKResponse(updatedCustomer);
    }

    @Override
    public CustomerResponse getCustomerById(long id) {
        if (id != 0L) {
            Optional<Customer> customer = customerRepository.findCustomerById(id);
            return customer.map(RequestResponse::getOKResponse).orElseGet(RequestResponse::getOKResponse);
        }
        return RequestResponse.getBADResponse("Invalid Customer Id");
    }

    @Override
    public CustomerResponse deleteCustomerById(long id) {
        if (id > 0) {
            Optional<Customer> customerOptional = customerRepository.findById(id);
            if (customerOptional.isPresent()) {
                customerRepository.deleteById(id);
                return RequestResponse.getOKResponse("Customer deleted successfully");
            } else {
                return RequestResponse.getBADResponse("Customer not found");
            }
        } else {
            return RequestResponse.getBADResponse("Invalid Customer Id");
        }
    }
@Override
public List<CustomerResponse> getAllCustomers() {

    List<Customer> customers = customerRepository.findAll();

    // Map Customer entities to CustomerResponse objects
    return customers.stream()
            .map(this::mapToCustomerResponse)
            .collect(Collectors.toList());
}
    private CustomerResponse mapToCustomerResponse(Customer customer) {
        // Map fields from Customer to CustomerResponse
        return CustomerResponse.builder()
                .result("OK")
                .data(customer)
                .success(true)
                .build();
    }

    @Override
    public List<CustomerResponse> searchCustomers(String query) {
        Customer exampleCustomer = new Customer();
        exampleCustomer.setFirstName(query);
        exampleCustomer.setLastName(query);
        if (exampleCustomer.getContactInformation() == null) {
            exampleCustomer.setContactInformation(new ContactInformation());
        }
        exampleCustomer.getContactInformation().setEmailAddress(query);
        exampleCustomer.setMobileNumber(query);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Customer> example = Example.of(exampleCustomer, exampleMatcher);
        List<Customer> customers = customerRepository.findAll(example);
        return customers.stream()
                .map(this::mapToCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse changeCustomerStatus(long id, String status) {
        if (id <= 0) {
            return RequestResponse.getBADResponse("Invalid Customer Id");
        }

        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            switch (status.toUpperCase()) {
                case "ACTIVE":
                    customer.setCustomerStatus(CustomerStatus.ACTIVE);
                    break;
                case "INACTIVE":
                    customer.setCustomerStatus(CustomerStatus.INACTIVE);
                    break;
                default:
                    return RequestResponse.getBADResponse("Invalid status. Accepted values: ACTIVE, INACTIVE");
            }
            customerRepository.save(customer);

            return RequestResponse.getOKResponse("Customer status changed successfully");
        } else {
            return RequestResponse.getBADResponse("Customer not found");
        }
    }
    // Helper method to update non-null fields
    private void updateNonNullFields(CustomerRequest source, Customer target)
            throws IllegalAccessException, InvocationTargetException {
        BeanUtilsBean notNullBean = new NullAwareBeanUtilsBean();
        notNullBean.copyProperties(target, source);
    }
    public static class NullAwareBeanUtilsBean extends BeanUtilsBean {
        @Override
        public void copyProperty(Object dest, String name, Object value) throws IllegalAccessException, InvocationTargetException {
            if (value != null) {
                super.copyProperty(dest, name, value);
            }
        }
    }

    public static CustomerResponse validateEmptyFields(CustomerRequest customerRequest) {
        if (customerRequest.getFirstName().isEmpty()
                || customerRequest.getLastName().isEmpty()){
            return RequestResponse.getBADResponse(AppConstants.MISSING_FIELD);
        }
        return null;
    }

    private Customer prepareCustomerRequest(CustomerRequest customerRequest){
        Customer customer = new Customer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setContactInformation(customerRequest.getContactInformation());
        customer.setCustomerType(customerRequest.getCustomerType());
        customer.setGender(customerRequest.getGender());
        customer.setIdNumber(customerRequest.getIdNumber());
        customer.setMobileNumber(customerRequest.getMobileNumber());
        customer.setJobTitle(customerRequest.getJobTitle());
        customer.setCustomerStatus(customerRequest.getCustomerStatus());
        customer.setDateOfBirth(customerRequest.getDateOfBirth());
        customer.setLanguage(customerRequest.getLanguage());
        customer.setContactInformation(customerRequest.getContactInformation());
        return customer;
    }
}
