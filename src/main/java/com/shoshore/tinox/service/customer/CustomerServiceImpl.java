package com.shoshore.tinox.service.customer;

import com.shoshore.tinox.entity.Customer;
import com.shoshore.tinox.model.CustomerRequest;
import com.shoshore.tinox.repository.CustomerRepository;
import com.shoshore.tinox.util.AppConstants;
import com.shoshore.tinox.util.CustomerResponse;
import com.shoshore.tinox.util.RequestResponse;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 25/10/2023, Wednesday
 **/
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        CustomerResponse MISSING_FIELD = validateEmptyFields(customerRequest);
        if (MISSING_FIELD != null) return MISSING_FIELD;
        Customer customer = prepareCustomerRequest(customerRequest);
        customer = customerRepository.save(customer);
        return RequestResponse.getOKResponse(customer);
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
