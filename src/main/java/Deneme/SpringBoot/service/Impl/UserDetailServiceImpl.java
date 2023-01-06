package Deneme.SpringBoot.service.Impl;

import Deneme.SpringBoot.dao.*;
import Deneme.SpringBoot.model.Address;
import Deneme.SpringBoot.model.Basket;
import Deneme.SpringBoot.model.Sales.Sales;
import Deneme.SpringBoot.model.SoldProducts;
import Deneme.SpringBoot.model.User;
import Deneme.SpringBoot.repository.AddressRepo;
import Deneme.SpringBoot.repository.BasketRepo;
import Deneme.SpringBoot.repository.SoldProductsRepo;
import Deneme.SpringBoot.repository.UserRepo;
import Deneme.SpringBoot.request.AddressDeleteReq;
import Deneme.SpringBoot.request.AddressReq;
import Deneme.SpringBoot.response.TransactionResponse;
import Deneme.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.aspectj.bridge.Version.SIMPLE_DATE_FORMAT;


@Service
public class UserDetailServiceImpl  implements UserDetailsService, UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    SoldProductsRepo soldProductsRepo;

    @Autowired
    BasketRepo basketRepo;

    @Autowired
    AddressRepo addressRepo;


    @Override
    public UserDAO getUserDAO(User user) {
        UserDAO userDAO = new UserDAO();
        userDAO.setUserId(user.getUserId());
        userDAO.setUsername(user.getUsername());
        userDAO.setFirstName(user.getFirstName());
        userDAO.setLastName(user.getLastName());
        userDAO.setEmail(user.getEmail());
        Boolean isAdmin = user.
                getUserRoles().
                stream().filter(role -> role.getRole().getName().equals("ADMIN")).findAny().isPresent();

        userDAO.setIsAdmin(isAdmin);

 List<BasketDAO> basket = user.getBasket().stream().map(this::getBasketDAO)
         .collect(Collectors.toList());
List<AddressDAO> address = user.getAddress().stream().map(this::getAddressDAO)
                .collect(Collectors.toList());

List<SoldProductDAO> orders = user.getSoldProducts().stream().map(this::getOrdersDAO)
                .collect(Collectors.toList());

//List<SalesDAO> sales = user.getSales().stream().map(this::getSalesDAO)
    //            .collect(Collectors.toList());
        userDAO.setAddress(address);
        userDAO.setBasket(basket);
        userDAO.setOrders(orders);
    //    userDAO.setTransaction(sales);


        return userDAO ;
    }

    private SoldProductDAO getOrdersDAO(SoldProducts soldProducts) {
    SoldProductDAO soldProductDAO = new SoldProductDAO();
    soldProductDAO.setId(soldProducts.getId());
    soldProductDAO.setProductId(soldProducts.getProductId());
    soldProductDAO.setPiece(soldProducts.getPiece());
    soldProductDAO.setAmount(soldProducts.getAmount());
    soldProductDAO.setDate(getDateAsString(soldProducts.getDate(),SIMPLE_DATE_FORMAT));
    return soldProductDAO;
    }

    private Date getDateAsString(Date date, String simpleDateFormat) {
Date date1 = new Date();

        return date1;
    }

    private SalesDAO getSalesDAO(Sales sales){
        SalesDAO salesDAO = new SalesDAO();
        salesDAO.setId(sales.getId());
        salesDAO.setDate(getDateAsString(sales.getDate(),SIMPLE_DATE_FORMAT));
        salesDAO.setAmount(sales.getAmount());
      //  salesDAO.setPayId(sales.getPay());
       // salesDAO.setAddressId(sales.getAddress()); // bu duzeltileek sales tablo duzelince

        return salesDAO;
    }

    private AddressDAO  getAddressDAO(Address address) {
       AddressDAO addressDAO = new AddressDAO();
       addressDAO.setId(address.getId());
       addressDAO.setAddressName(address.getAddressName());
        addressDAO.setFirstName(address.getFirstName());
        addressDAO.setLastName(address.getLastName());
        addressDAO.setCountry(address.getCountry());
        addressDAO.setCity(address.getCity());
        addressDAO.setStreet(address.getStreet());
        addressDAO.setStreetNo(address.getStreetNo());
        addressDAO.setPostCode(address.getPostCode());

        return addressDAO;
    }

    private BasketDAO getBasketDAO(Basket basket) {
        BasketDAO basketDAO = new BasketDAO();
        basketDAO.setId(basket.getId());
        basketDAO.setProductId(basket.getProductId());
        basketDAO.setPiece(basket.getPiece());
        return basketDAO;
    }


    @Override
    public UserDAO getUserDAOByName(String username) {
        UserDAO userDAO = null;
        Optional<User> user = userRepo.findByUsername(username);
        if(user.isPresent()){
            userDAO = getUserDAO(user.get());
        }
        return userDAO;
    }

    @Override
    public List<UserDAO> getAllUsers() {
        List<User> users = (List<User>) userRepo.findAll();
        return users.stream().map(this::transformUsers).collect(Collectors.toList());
    }

    // bir adrws eklendigi zaman tekrardan tm bilgiler derlenip gonderiliyor
    // bu gereksiz is gucu olusturuyor
    // sadece adres eklendi onayi gonderilip FE de adres adres list ine eklenebilir.
    @Override
    public TransactionResponse addAddress(User user, AddressReq addressReq) {
        TransactionResponse response = new TransactionResponse();
        Address address = new Address(
                addressReq.getAddressName(),
                addressReq.getFirstName(),
                addressReq.getLastName(),
                addressReq.getCountry(),
                addressReq.getCity(),
                addressReq.getStreet(),
                addressReq.getStreetNo(),
                addressReq.getPostCode());
        address.setUser(user);
        try {
            addressRepo.save(address);
            response.setMessage("Address add successfuly");
            response.setSuccess(true);
        }catch (Exception e){
            response.setMessage("Address add Unsuccessfuly");
            response.setSuccess(false);
        }
        UserDAO userDAO = getUserDAOByName(user.getUsername());
        response.setUser(userDAO);
        return response;

    }

    //hicbir adres silinmiyor, kullanici adi sistem olarak degistiriliyor
    // satis yapilanislemlerde kullanilmaihtimalindendolayi sales tablosunda daimi olarak kullanilacak
    // hiccbir yerde kullanilmamis system adresleri belli bir sure sonra otomatik silinebilir.
    @Override
    public TransactionResponse deleteAddress(User user, AddressDeleteReq addressReq) {
        TransactionResponse response = new TransactionResponse();
        String username ="System";
        User user2 = (User) loadUserByUsername(username);
        Address address = new Address(
                addressReq.getId(),
                addressReq.getAddressName(),
                addressReq.getFirstName(),
                addressReq.getLastName(),
                addressReq.getCountry(),
                addressReq.getCity(),
                addressReq.getStreet(),
                addressReq.getStreetNo(),
                addressReq.getPostCode());
        address.setUser(user2);
        try {
            addressRepo.save(address);
            response.setMessage("Address delete successfuly");
            response.setSuccess(true);
        }catch (Exception e){
            response.setMessage("Address delete Unsuccessfuly");
            response.setSuccess(false);
        }
        UserDAO userDAO = getUserDAOByName(user.getUsername());
        response.setUser(userDAO);
        return response;
    }

    public UserDAO transformUsers(User user) {
        UserDAO userDAO = new UserDAO();
        userDAO.setUserId(user.getUserId());
        userDAO.setEmail(user.getEmail());
        userDAO.setUsername(user.getUsername());
        userDAO.setFirstName(user.getFirstName());
        userDAO.setLastName(user.getLastName());

        return userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username Not Found " + username));
        return user;
    }
}
