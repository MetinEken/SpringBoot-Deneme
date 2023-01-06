package Deneme.SpringBoot.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetRoleReq {

    private Long userId;
    private String roleName;
}
