package Deneme.SpringBoot.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BasketGuestReq {
    private Long productId;
    private Long piece;
    private String userJwt;
}
