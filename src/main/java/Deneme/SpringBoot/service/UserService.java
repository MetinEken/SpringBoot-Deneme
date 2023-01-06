package Deneme.SpringBoot.service;

import Deneme.SpringBoot.dao.UserDAO;
import Deneme.SpringBoot.model.User;
import Deneme.SpringBoot.request.AddressDeleteReq;
import Deneme.SpringBoot.request.AddressReq;
import Deneme.SpringBoot.response.TransactionResponse;


import java.util.List;

public interface UserService  {

    UserDAO getUserDAO(User user);
    UserDAO getUserDAOByName(String username);
    List<UserDAO> getAllUsers();

TransactionResponse addAddress(User user, AddressReq addressReq);

    TransactionResponse deleteAddress(User user, AddressDeleteReq addressDeleteReq);
}
