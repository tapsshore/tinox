package com.shoshore.tinox.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 25/10/2023, Wednesday
 **/
@Embeddable
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactInformation {
    private String emailAddress;
    private String phoneNumber;
    @Embedded
    private Address address;
}
