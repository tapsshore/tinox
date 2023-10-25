package com.shoshore.tinox.repository;

import com.shoshore.tinox.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 25/10/2023, Wednesday
 **/
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
