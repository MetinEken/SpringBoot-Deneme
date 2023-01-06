package Deneme.SpringBoot.service;

import Deneme.SpringBoot.model.User;
import Deneme.SpringBoot.request.BuyingRequest;
import Deneme.SpringBoot.response.BuyingResponse;
import Deneme.SpringBoot.response.Response;
import org.springframework.http.ResponseEntity;

public interface BuyingService {

    ResponseEntity<BuyingResponse> saveBuying(User user, BuyingRequest buyingRequest);
}
