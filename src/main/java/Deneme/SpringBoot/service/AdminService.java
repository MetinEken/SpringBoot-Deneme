package Deneme.SpringBoot.service;

import Deneme.SpringBoot.model.Products;
import Deneme.SpringBoot.request.ProductReq;
import Deneme.SpringBoot.request.ProductsUpdateRequest;
import Deneme.SpringBoot.request.SetRoleReq;
import Deneme.SpringBoot.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {

ResponseEntity<Response> setRole(SetRoleReq setRoleReq);

    ResponseEntity<Response> saveProduct(ProductReq productReq);

    List<Products> allProducts();

    ResponseEntity<Response> updateProduct(ProductsUpdateRequest productsUpdateRequest);
}
