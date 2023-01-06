package Deneme.SpringBoot.response;

import Deneme.SpringBoot.dao.UserDAO;
import lombok.Data;

@Data
public class TransactionResponse {
    boolean isSuccess;
    String message;
    UserDAO user;
}
