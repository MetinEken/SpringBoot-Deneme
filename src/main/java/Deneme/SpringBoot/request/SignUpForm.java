package Deneme.SpringBoot.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SignUpForm {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String role="USER";
}
