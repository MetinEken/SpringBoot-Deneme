package Deneme.SpringBoot.dao;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDAO {
    private Long userId;
    private String username;
    private String firstName;
    private String  lastName;
    private String email;
    private Boolean isAdmin;
    @JsonIgnore
    private String password;
    private List<AddressDAO> address;
    private List<BasketDAO> basket;
    private List<SoldProductDAO> orders;
    private List<SalesDAO> transaction;


}
