package Deneme.SpringBoot.repository;

import Deneme.SpringBoot.model.SoldProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldProductsRepo extends JpaRepository<SoldProducts, Long> {
}
