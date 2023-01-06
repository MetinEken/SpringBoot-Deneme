package Deneme.SpringBoot.request;


import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter

public class LoginForm {
    @NotBlank
    @Size(min=3, max=10)
    private String username;

    @NotBlank

    private String password;
}
