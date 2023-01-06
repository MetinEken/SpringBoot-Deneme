package Deneme.SpringBoot.service;

import Deneme.SpringBoot.dao.BasketDAO;
import Deneme.SpringBoot.model.Basket;
import Deneme.SpringBoot.model.User;
import Deneme.SpringBoot.request.BasketListReq;
import Deneme.SpringBoot.request.BasketReq;
import Deneme.SpringBoot.request.BasketUpdateReq;
import Deneme.SpringBoot.response.TransactionResponse;

import java.util.List;

public interface BasketService {

   TransactionResponse saveBasket(User user,BasketReq basketReq);
   TransactionResponse deleteProduct(User user, BasketUpdateReq basketUpdateReq);
   TransactionResponse updateBasket(User user, BasketUpdateReq basketUpdateReq);
   TransactionResponse deleteALlBasket(User user, BasketListReq basketListReq);

   List<BasketDAO> getAllBasket();

   List<BasketDAO> getMyBasket(Long id);

   List<Basket> isThere(Long id, Long pId);

  // Basket againAdd(User user, );


}
