package Deneme.SpringBoot.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDAO {

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
