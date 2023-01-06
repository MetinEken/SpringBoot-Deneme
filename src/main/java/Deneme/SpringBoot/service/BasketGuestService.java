package Deneme.SpringBoot.service;

import Deneme.SpringBoot.dao.BasketGuestDAO;
import Deneme.SpringBoot.model.BasketGuest;
import Deneme.SpringBoot.request.BasketGuestReq;

public interface BasketGuestService {

    void addBasket(String name, BasketGuestReq basketGuestReq);

    BasketGuestDAO getBasketGuestDAO(String name);
}
