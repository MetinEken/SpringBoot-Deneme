package Deneme.SpringBoot.service.Impl;


import Deneme.SpringBoot.dao.BasketDAO;
import Deneme.SpringBoot.dao.UserDAO;
import Deneme.SpringBoot.model.Basket;
import Deneme.SpringBoot.model.User;
import Deneme.SpringBoot.repository.BasketRepo;
import Deneme.SpringBoot.repository.UserRepo;
import Deneme.SpringBoot.request.BasketListReq;
import Deneme.SpringBoot.request.BasketReq;
import Deneme.SpringBoot.request.BasketUpdateReq;

import Deneme.SpringBoot.response.TransactionResponse;
import Deneme.SpringBoot.service.BasketService;
import Deneme.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    BasketRepo basketRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserDetailServiceImpl userDetailService;
@Autowired
    UserService userService;

    // burada basketServi i Autowired yapmisim yanlislikla, ve ters donguye girdi,
    // program bu yuzden calismadi cycle bean hatasi verdi

// ===== ADD PRODUCT TO BASKET ====
    // eger ayni hesaba
    @Override
    public TransactionResponse saveBasket(User user, BasketReq basketReq) {
        TransactionResponse response = new TransactionResponse();
        // 1- burada kullanicinin sepetinde eklemekistedigi urun var mi yok mu ona bakmak gerekiyor
        // 2- eger urun kisinin sepetinde zaten var ise basketUpdate metoduna yonlendirmek gerekir
        // 3- olan urunu tekrar eklemek ayni urunu farkliymis gibi islem yapmasina sebep oluyor.

   //     List<Basket> basket1 = user.getBasket().stream().map(basketService.getBasket(User user)).collect(Collectors.toList());

      //        List<BasketDAO> basket1 = user.getBasket().stream().map(userDetailService.getBasketDAO)
      //                .collect(Collectors.toList());

        Basket basket = new Basket(
                basketReq.getProductId(),
                basketReq.getPiece());
        basket.setUser(user);
        try {
            basketRepo.save(basket);
            response.setMessage("Product add successfuly");
            response.setSuccess(true);
        }catch (Exception e){
            response.setMessage("Product add Unsuccessfuly");
            response.setSuccess(false);
        }
       basketRepo.save(basket);
        UserDAO userDAO = userService.getUserDAOByName(user.getUsername());

        response.setUser(userDAO);
        return response;

    }

        // ====== DELETE PRODUCT FROM BASKET =====
    @Override
    public TransactionResponse deleteProduct(User user, BasketUpdateReq basketUpdateReq) {
        TransactionResponse response = new TransactionResponse();
        Basket basket = new Basket(basketUpdateReq.getId(),
                basketUpdateReq.getProductId(),
                basketUpdateReq.getPiece());
        basket.setUser(user);
        System.out.println("  ====  service ==== buraya gelindi");
        try {
            basketRepo.delete(basket);
            response.setMessage("Product delete successfuly");
            response.setSuccess(true);
        }catch (Exception e){
            response.setMessage("Product delete Unsuccessfuly");
            response.setSuccess(false);
        }
        UserDAO userDAO = userService.getUserDAOByName(user.getUsername());
        response.setUser(userDAO);
        return  response;
    }

    // =====  UPDATE PRODUCT IN BASKET  ======

    // sepette olan urun, basket id si ile birlikte guncellleniyor(artiriliyor ya da Azaltiliyor).
    // FE den urun id si ve urun id si ile birlikte istek yapilmasi gerekiyor
    // bu method sadece spetteki urunlerin sayisini azaltip artiriyor.
    // eger sepette urunden 1 adet var ise guncellemede urun adeti 0 yapilir ise, urun sepetten silinecek mi, urun adeti 0 mi yazilacak
    // yukardai ornegi amazondan bir bakalim
    // amazona urun adet=0 ile birlikte urunu sil methodu calisiyor
    // yani FE den urun adet sifir yapilirsa Update degil Delete istegi yapilacak!!!

    // Response hatali, sadece onay gondermek yada ilgili yeri gondermekmantikli !!!
    // Bakc-End de basketteki urun silinir ama cevap iletiminde sikinti olursa front end de onay
    // mesaji alinamayacagi icin UF de guncelleme olmayacak
    // tekrar urun silme yada update istegi giderse Backend de hata olusacak ve oncesinden silinmis urun tekrar silinemeyecek
    // bu nedenle silinmis urun tekrar tekrar silinmeye caisailacak ve kisir bir donguye girilmis olunacak
    // bu nedenle responsa sadece ilgili bolumun tamamini gonderilerek cozulebilir. (basketDAO gibi)

    @Override
    public TransactionResponse updateBasket(User user, BasketUpdateReq basketUpdateReq) {
        TransactionResponse response = new TransactionResponse();
        Basket basket = new Basket(basketUpdateReq.getId(),
                basketUpdateReq.getProductId(),
                basketUpdateReq.getPiece());
        basket.setUser(user);
        try {
            basketRepo.save(basket);
            response.setMessage("Product Update successfuly");
            response.setSuccess(true);
        }catch (Exception e){
            response.setMessage("Product Update Unsuccessfuly");
            response.setSuccess(false);
        }
     UserDAO userDAO = userService.getUserDAOByName(user.getUsername());
        response.setUser(userDAO);
    return response;
    }


    // ===== DELETE ALL PRODUCTS FROM BASKET ===
    // amazon da tum sepeti sil gibi ir select yok!!
    @Override
    public TransactionResponse deleteALlBasket(User user, BasketListReq basketListReq) {
        TransactionResponse response = new TransactionResponse();
        System.out.println("  ====  1 ==== buraya gelindi");
        List<Basket> basket = basketListReq.getBasketList();

        System.out.println("  ====  2 ==== buraya gelindi");
        try {
           basketRepo.deleteAll(basket);
            response.setMessage("Baskets Delete successfuly");
            response.setSuccess(true);
            System.out.println("  ====  3 ==== buraya gelindi");
        }catch (Exception e){
            response.setMessage("Baskets Delete Unsuccessfuly");
            response.setSuccess(false);
        }
        UserDAO userDAO = userService.getUserDAOByName(user.getUsername());
        response.setUser(userDAO);
        return response;
    }
// butun sepeti getiriyor BasketDAO formatinda, Basket olarak yazdirmiyor.
    @Override
    public List<BasketDAO> getAllBasket() {
        List<Basket> basket = (List<Basket>) basketRepo.findAll();
        return basket.stream().map(this::getBasketDAO).collect(Collectors.toList());
    }

    @Override
    public List<BasketDAO> getMyBasket(Long id) {
        List<Basket> basket = (List<Basket>) basketRepo.getbasketByUserId(id);
        return basket.stream().map(this::getBasketDAO).collect(Collectors.toList());
    }

    @Override
    public  List<Basket> isThere(Long id, Long pId) {
     //   List<Basket> basket = basketRepo.findByx(id, pId);
        return null;
    }

//    @Override
//    public Basket againAdd() {
//        return null;
//    }

    private BasketDAO getBasketDAO(Basket basket) {
            BasketDAO basketDAO = new BasketDAO();
            basketDAO.setId(basket.getId());
            basketDAO.setProductId(basket.getProductId());
            basketDAO.setPiece(basket.getPiece());
            return basketDAO;
        }

//    @Override
//    public List<Basket> myBasket(User user) {
//
//        List<Basket> basket= basketRepo.getbasketByUser(user.getUserId());
//        return basket;
//    }


}
