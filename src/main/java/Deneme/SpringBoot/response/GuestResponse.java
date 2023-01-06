package Deneme.SpringBoot.response;

import Deneme.SpringBoot.dao.BasketGuestDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GuestResponse {

    boolean isSuccess;
    String message;
    BasketGuestDAO basketGuestDAO;

    public GuestResponse() {

    }
}
