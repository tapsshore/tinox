package com.shoshore.tinox.model;

import com.shoshore.tinox.entity.ContactInformation;
import com.shoshore.tinox.enums.CustomerStatus;
import com.shoshore.tinox.enums.CustomerType;
import com.shoshore.tinox.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 25/10/2023, Wednesday
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private ContactInformation contactInformation;
    private CustomerType customerType;
    private Gender gender;
    private String idNumber;
    private String mobileNumber;
    private String jobTitle;
    private CustomerStatus customerStatus;
    private LocalDate dateOfBirth;
    private String language;
}
