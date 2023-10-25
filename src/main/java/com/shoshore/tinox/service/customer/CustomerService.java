package com.shoshore.tinox.service.customer;

import com.shoshore.tinox.model.CustomerRequest;
import com.shoshore.tinox.util.CustomerResponse;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 25/10/2023, Wednesday
 **/
public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);

}
