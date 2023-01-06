package Deneme.SpringBoot.service.Impl;

import Deneme.SpringBoot.model.Address;
import Deneme.SpringBoot.model.Sales.Sales;
import Deneme.SpringBoot.model.User;
import Deneme.SpringBoot.repository.BasketRepo;
import Deneme.SpringBoot.repository.SalesRepo;
import Deneme.SpringBoot.request.BasketListReq;
import Deneme.SpringBoot.request.BuyingRequest;
import Deneme.SpringBoot.response.BuyingResponse;
import Deneme.SpringBoot.service.BasketService;
import Deneme.SpringBoot.service.BuyingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BuyingServiceImpl implements BuyingService {

    @Autowired
    BasketRepo basketRepo;

    @Autowired
    SalesRepo salesRepo;

    @Autowired
    BasketService basketService;

    @Override
    public ResponseEntity<BuyingResponse> saveBuying(User user, BuyingRequest buyingRequest) {
        System.out.println("buraya gelindi 2");
        BuyingResponse response = new BuyingResponse();
       // Address address = new Address(buyingRequest.getAddress());
        Sales sales = new Sales();
//        System.out.println("buraya gelindi 2.1.");
//                 sales.setDate(buyingRequest.getSales().getDate());
//        System.out.println("buraya gelindi 2.1.1");
//                 sales.setAmount(buyingRequest.getSales().getAmount());
//        System.out.println("buraya gelindi 2.1.2");
//                 sales.setMoney(buyingRequest.getSales().getMoney());
//        System.out.println("buraya gelindi 2.1.3");
//                 sales.setPayMethod(buyingRequest.getSales().getPayMethod());
//        System.out.println("buraya gelindi 2.1.4");
//                 sales.setUserId(buyingRequest.getSales().getUserId());
//        System.out.println("buraya gelindi 2.1.5");
//                 sales.setAddressId(buyingRequest.getAddress().getId());
        System.out.println("buraya gelindi 2.2.6");
        BasketListReq basketListReq = new BasketListReq();
        System.out.println("buraya gelindi 2.2.7");
        basketListReq.setBasketList(buyingRequest.getBasket());
        System.out.println("buraya gelindi 2.3");
//         private Date date;
//    private double amount;
//    private String money;
//    private String payMethod;
//    private Long userId;
//    private Long addressId;
        try {
            salesRepo.save(buyingRequest.getSales());
            System.out.println("buraya gelindi 3");
            response.setSuccess(true);
            response.setBuyingId(198765L);
            response.setMessage(response.getBuyingId() +" numarali isleminiz basari ile gerceklesmistir. Kisa sure icerisinde islem detalari email adresinize gonderilecektir");

        }catch (Exception e){
            System.out.println("=======================================");
            System.out.println("==== sales kaydedilemedi ====");
        }
        try{
            basketService.deleteALlBasket(user, basketListReq);
        }catch (Exception e){
            System.out.println("=======================================");
            System.out.println(" ========== sepetteki urunler satis sonrasi silinemedi =====");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    private Long creatBuyId() {
//        return
//    }
}
