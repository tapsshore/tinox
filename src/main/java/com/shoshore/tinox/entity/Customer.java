package com.shoshore.tinox.entity;

import com.shoshore.tinox.enums.CustomerStatus;
import com.shoshore.tinox.enums.CustomerType;
import com.shoshore.tinox.enums.Gender;
import jakarta.persistence.*;
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
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    @Embedded
    private ContactInformation contactInformation;
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String idNumber;
    private String mobileNumber;
    private String jobTitle;
    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;
    private LocalDate dateOfBirth;
    private LocalDate dateCreated;
    private LocalDate dateUpdate;
    private String language;
    @Version
    private long version;
}
