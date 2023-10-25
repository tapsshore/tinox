package com.shoshore.tinox.service.customer;

import com.shoshore.tinox.entity.Customer;
import com.shoshore.tinox.model.CustomerRequest;
import com.shoshore.tinox.repository.CustomerRepository;
import com.shoshore.tinox.util.AppConstants;
import com.shoshore.tinox.util.CustomerResponse;
import com.shoshore.tinox.util.RequestResponse;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

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
        updateNonNullFields(customerRequest, existingPolicy);

        Customer updatedCustomer = customerRepository.save(existingPolicy);
        return RequestResponse.getOKResponse(updatedCustomer);
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
