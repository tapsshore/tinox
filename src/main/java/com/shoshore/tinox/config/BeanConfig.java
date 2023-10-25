package com.shoshore.tinox.config;

import com.shoshore.tinox.repository.CustomerRepository;
import com.shoshore.tinox.service.customer.CustomerService;
import com.shoshore.tinox.service.customer.CustomerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 25/10/2023, Wednesday
 **/
@Configuration
@EnableAsync
public class BeanConfig {

    @Bean
    public CustomerService customerService(CustomerRepository customerRepository){
        return new CustomerServiceImpl(customerRepository);
    }
}
