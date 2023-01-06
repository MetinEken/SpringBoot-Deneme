package Deneme.SpringBoot.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AddressDeleteReq {
    private Long id;
    private String addressName;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String street;
    private String streetNo;
    private String postCode;
}
