package Deneme.SpringBoot.service.Impl;

import Deneme.SpringBoot.dao.BasketGuestDAO;
import Deneme.SpringBoot.dao.UserDAO;
import Deneme.SpringBoot.model.BasketGuest;
import Deneme.SpringBoot.model.User;
import Deneme.SpringBoot.repository.BasketGuestRepo;
import Deneme.SpringBoot.request.BasketGuestReq;
import Deneme.SpringBoot.service.BasketGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasketGuestServiceImpl implements BasketGuestService {

   @Autowired
    BasketGuestRepo basketGuestRepo;


    @Override
    public void addBasket(String name, BasketGuestReq basketGuestReq) {
        BasketGuest basketGuest = new BasketGuest(basketGuestReq.getProductId(),
                basketGuestReq.getPiece());
        basketGuest.setUserJwt(name);
        basketGuestRepo.save(basketGuest);
    }

    @Override
    public BasketGuestDAO getBasketGuestDAO(String name) {
      BasketGuestDAO basketGuestDAO = null;
      basketGuestDAO = getBasketGuestDAO(name);


//        UserDAO userDAO = null;
//        Optional<User> user = userRepo.findByUsername(username);
//        if(user.isPresent()){
//            userDAO = getUserDAO(user.get());
//        }
//        return userDAO;

        return null;
    }
}
