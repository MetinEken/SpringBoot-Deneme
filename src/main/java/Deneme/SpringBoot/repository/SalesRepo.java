package Deneme.SpringBoot.repository;


import Deneme.SpringBoot.model.Sales.Sales;
import org.springframework.data.repository.CrudRepository;

public interface SalesRepo extends CrudRepository<Sales, Long> {

}
