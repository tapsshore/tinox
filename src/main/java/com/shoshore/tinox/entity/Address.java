package com.shoshore.tinox.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 25/10/2023, Wednesday
 **/
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String streetNameAndNumber;
    private String city;
    private String state;
    private String zipCode;

}
