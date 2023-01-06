package Deneme.SpringBoot.controller;


import Deneme.SpringBoot.model.Products;
import Deneme.SpringBoot.repository.RoleRepo;
import Deneme.SpringBoot.repository.UserRepo;
import Deneme.SpringBoot.request.ProductReq;
import Deneme.SpringBoot.request.ProductsUpdateRequest;
import Deneme.SpringBoot.request.SetRoleReq;
import Deneme.SpringBoot.response.Response;
import Deneme.SpringBoot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @PatchMapping("/setrole")
    public ResponseEntity<Response> adRole(@RequestBody SetRoleReq setRoleReq){
        return adminService.setRole(setRoleReq);
    }

    @PostMapping("/saveproduct")
    public ResponseEntity<Response> saveProduct(@RequestBody ProductReq productReq){
        System.out.println("buraya geldik 1");
        return adminService.saveProduct(productReq);
    }

    @GetMapping("/allProduct")
    public List<Products> allProduct(){
      return   adminService.allProducts();
    }

    // en son burada kalindi
    // guncellemedi, yeni kayit yapti
    @PatchMapping("/updateproduct")
    public ResponseEntity<Response> updateProduct(@RequestBody ProductsUpdateRequest productsUpdateRequest){
        return adminService.updateProduct(productsUpdateRequest);
    }

    }
