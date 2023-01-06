package Deneme.SpringBoot.controller;

import Deneme.SpringBoot.dao.BasketDAO;
import Deneme.SpringBoot.dao.UserDAO;
import Deneme.SpringBoot.model.Basket;
import Deneme.SpringBoot.model.User;
import Deneme.SpringBoot.repository.BasketRepo;
import Deneme.SpringBoot.request.*;
import Deneme.SpringBoot.response.BuyingResponse;
import Deneme.SpringBoot.response.Response;
import Deneme.SpringBoot.response.TransactionResponse;
import Deneme.SpringBoot.service.BasketService;
import Deneme.SpringBoot.service.BuyingService;
import Deneme.SpringBoot.service.Impl.UserDetailServiceImpl;
import Deneme.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/user")
public class BasketController {

    @Autowired
    BasketService basketService;

    @Autowired
    UserDetailServiceImpl UserDetailServiceImpl;

    @Autowired
    UserService userService;

    @Autowired
    BasketRepo basketRepo;

    @Autowired
    BuyingService buyingService;


// tum sepeti getiriyor, sadece analiz icin Admin e gerekli olabilir.
    @GetMapping("/getbasket")
    public List<BasketDAO> getirBasket( ){
        return basketService.getAllBasket();
    }

    // !!! kendi sepetini gonderirkenkarsilasilan hata !!! List<BAsket> return olmuyor.
    // bunun yerine bu list i BadketDAO ya cevirerek return yaptik oldu.
    // User in kendi sepetini gonderen method

    @GetMapping("/getbasket/{id}")
    List<BasketDAO> byId(@PathVariable Long id){
        return basketService.getMyBasket(id);
    }

// Response olarak tekrardan tum bilgileri derleyip gonderiyoruz.
// !! bu bir hata, sadece ilgili yeri ya da onayi gondermek yeterli olacak
    @PatchMapping("/updatebasket")
    public ResponseEntity<TransactionResponse> addBasket (@Valid @RequestBody BasketUpdateReq basketUpdateReq){
        TransactionResponse response = new TransactionResponse();
       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     response =  basketService.updateBasket(user, basketUpdateReq);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PostMapping("/addbasket")
    public ResponseEntity<TransactionResponse> addProduct(@Valid @RequestBody BasketReq basketReq ){
        TransactionResponse response = new TransactionResponse();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // kaydetmek istedigimiz yeni urun varsa!!! database den bulup buraya geldi, // onceden bir list donduruyordu onu obje olarak aldik. tekrar List yapatik
// aldigimiz listi stream ve foreach ile icerisine girip database e ekledik.
        // ayni urun farkli id no ile ayni user a kaydedilmis olabilir. kullanici her iki kayidi da gorecek, istdigini silecek
        // ayni urunu farkli kaydedersek bunu biz silmeyecegiz.
        // return lerde hata devam ediyor, ekrar ayni bilgileri dondurmek is yukunu artiriyor.
        List<Basket> basket = basketRepo.getBasketByUserAndProductId(user.getUserId(), basketReq.getProductId());
        if (basket.size() >0){
            basket.stream().forEach(n -> transformBasket(n,basketReq, user));
            response.setSuccess(true);
            response.setMessage("islem basarili");
            UserDAO userDAO = userService.getUserDAOByName(user.getUsername());
            response.setUser(userDAO);
           }else {
            response= basketService.saveBasket(user, basketReq);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private BasketUpdateReq transformBasket(Basket basket, BasketReq basketReq, User user) {
        BasketUpdateReq basketUpdateReq = new BasketUpdateReq();
        basketUpdateReq.setId(basket.getId());
        basketUpdateReq.setProductId(basket.getProductId());
        basketUpdateReq.setPiece(basket.getPiece()+basketReq.getPiece());
        basketService.updateBasket(user,basketUpdateReq);
        return basketUpdateReq;
    }


    @DeleteMapping("/deletebasket")
    public ResponseEntity<TransactionResponse> deletebasket(@Valid @RequestBody BasketUpdateReq basketUpdateReq){
        System.out.println("  ====  controller ==== buraya gelindi");
        TransactionResponse response = new TransactionResponse();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    response =    basketService.deleteProduct(user, basketUpdateReq);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // List seklinde gelen istekleri alabilmek icin ozel List classi olusturduk.
    // postman yazimi ise tek bir obje gibi ama icerisinde [] olan bir istek
    @DeleteMapping("/deleteallbasket")
    public ResponseEntity<TransactionResponse> deleteAllBasket(@Valid @RequestBody BasketListReq<Basket> basketList){
        System.out.println("  ====  controller ==== buraya gelindi");
        TransactionResponse response = new TransactionResponse();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      response = basketService.deleteALlBasket(user, basketList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PatchMapping("/buying")
    public ResponseEntity<BuyingResponse> buying(@Valid @RequestBody BuyingRequest buyingRequest){
        System.out.println("buraya gelindi 1");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return  buyingService.saveBuying(user, buyingRequest);
    }

}
