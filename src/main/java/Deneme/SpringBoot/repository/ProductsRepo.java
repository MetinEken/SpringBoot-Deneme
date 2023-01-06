package Deneme.SpringBoot.repository;

import Deneme.SpringBoot.model.Products;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepo extends CrudRepository<Products, Long> {

}
