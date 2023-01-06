package Deneme.SpringBoot.controller;

import Deneme.SpringBoot.request.BasketGuestReq;
import Deneme.SpringBoot.response.GuestResponse;
import Deneme.SpringBoot.service.BasketGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    BasketGuestService basketGuestService;

    @PostMapping("/addbasket/{name}")
    public ResponseEntity<GuestResponse> addBasket(@PathVariable String name, @RequestBody BasketGuestReq basketGuestReq){
        GuestResponse response = new GuestResponse();
        basketGuestService.addBasket(name, basketGuestReq);
        response.setMessage("this product added your basket");
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
