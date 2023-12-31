package com.shoshore.tinox.service.customer;

import com.shoshore.tinox.model.CustomerRequest;
import com.shoshore.tinox.util.CustomerResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 25/10/2023, Wednesday
 **/
public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    CustomerResponse updateCustomerById(CustomerRequest customerRequest) throws InvocationTargetException, IllegalAccessException;

    CustomerResponse getCustomerById(long id);

    CustomerResponse deleteCustomerById(long id);

    List<CustomerResponse> getAllCustomers();

    List<CustomerResponse> searchCustomers(String query);

    CustomerResponse changeCustomerStatus(long id, String status);
}
