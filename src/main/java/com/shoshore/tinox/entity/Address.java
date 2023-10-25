package com.shoshore.tinox.entity;

import jakarta.persistence.Embeddable;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 25/10/2023, Wednesday
 **/
@Embeddable
public class Address {
    private String streetNameAndNumber;

    private String city;

    private String state;

    private String zipCode;

}
