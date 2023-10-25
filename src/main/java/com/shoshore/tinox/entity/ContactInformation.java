package com.shoshore.tinox.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 25/10/2023, Wednesday
 **/
@Embeddable
@Getter
@Setter
public class ContactInformation {
    private String emailAddress;

    private String phoneNumber;

    @Embedded
    private Address address;
}
