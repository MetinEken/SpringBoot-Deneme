package Deneme.SpringBoot.controller;

import Deneme.SpringBoot.model.User;
import Deneme.SpringBoot.request.AddressDeleteReq;
import Deneme.SpringBoot.request.AddressReq;
import Deneme.SpringBoot.response.TransactionResponse;
import Deneme.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/addaddress")
    public ResponseEntity<TransactionResponse> addAddress(@Valid @RequestBody AddressReq addressReq){
        TransactionResponse response = new TransactionResponse();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        response = userService.addAddress(user, addressReq);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PatchMapping("/deleteaddress")
    public ResponseEntity<TransactionResponse> deleteAddress(@Valid @RequestBody AddressDeleteReq addressDeleteReq){
        TransactionResponse response = new TransactionResponse();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        response = userService.deleteAddress(user, addressDeleteReq);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

}
