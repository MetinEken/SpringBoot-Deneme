package Deneme.SpringBoot.service.Impl;

import Deneme.SpringBoot.model.Products;
import Deneme.SpringBoot.model.Role;
import Deneme.SpringBoot.model.User;
import Deneme.SpringBoot.model.UserRole;
import Deneme.SpringBoot.repository.ProductsRepo;
import Deneme.SpringBoot.repository.RoleRepo;
import Deneme.SpringBoot.repository.UserRepo;
import Deneme.SpringBoot.request.ProductReq;
import Deneme.SpringBoot.request.ProductsUpdateRequest;
import Deneme.SpringBoot.request.SetRoleReq;
import Deneme.SpringBoot.response.Response;
import Deneme.SpringBoot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    ProductsRepo productsRepo;

    @Override
    public ResponseEntity<Response> setRole(SetRoleReq setRoleReq) {
        Response response = new Response();
        User user = userRepo.findById(setRoleReq.getUserId()).orElseThrow(() -> new IllegalStateException(setRoleReq.getUserId() + " id'li kisi bulunamadi"));

        Role role = roleRepo.findByName(setRoleReq.getRoleName()).orElseThrow(() -> new IllegalStateException(setRoleReq.getRoleName() + " isimli ROLE bulunamadi"));
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));

        user.setUserRoles(userRoles);

        userRepo.save(user);
        response.setMessage(
                "email : " + user.getEmail() + "" + " kullanici Adi : " + user.getUsername() + " kullanicisina " + setRoleReq.getRoleName() + " Role eklendi");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Response> saveProduct(ProductReq productReq) {
        System.out.println("buraya geldik 2");
        Response response = new Response();
        Products products = new Products();
        products.setName(productReq.getName());
        products.setType(productReq.getType());
        products.setFe1(productReq.getFe1());
        products.setFe2(productReq.getFe2());
        products.setFe3(productReq.getFe3());
        try {
            productsRepo.save(products);
            response.setSuccess(true);
            response.setMessage(products.getName() +" isimli urun basari ile eklenmistir");
            System.out.println("buraya geldik 3");
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(products.getName() +" isimli urun Kaydedilmedi!!!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
       // return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public List<Products> allProducts() {
        List<Products> products = (List<Products>) productsRepo.findAll();
        return products;
    }

    @Override
    public ResponseEntity<Response> updateProduct(ProductsUpdateRequest productsUpdateRequest) {
        Response response = new Response();
        Products products = productsRepo.findById(productsUpdateRequest.getId()).orElseThrow(() -> new IllegalStateException(productsUpdateRequest.getId()+" id'li kisi bulunamadi"));
if (productsUpdateRequest.getName() != null){
    products.setName(productsUpdateRequest.getName());
}
if (productsUpdateRequest.getName() != null){
    products.setName(productsUpdateRequest.getName()
    );
}if (productsUpdateRequest.getType() != null){
    products.setType(productsUpdateRequest.getType());
}if (productsUpdateRequest.getFe3() != null){
    products.setFe1(productsUpdateRequest.getFe1());
}if (productsUpdateRequest.getFe2() != null){
    products.setFe2(productsUpdateRequest.getFe2());
}if (productsUpdateRequest.getFe3() != null){
    products.setFe3(productsUpdateRequest.getFe3());
}
        try {productsRepo.save(products);
            response.setSuccess(true);
            response.setMessage(productsUpdateRequest.getName() +" isimli urun guncellenmistir!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(productsUpdateRequest.getName() +" isimli urun Guncellenemedi!!!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
}
