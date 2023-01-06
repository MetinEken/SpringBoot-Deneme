package Deneme.SpringBoot.repository;

import Deneme.SpringBoot.dao.BasketDAO;
import Deneme.SpringBoot.model.Basket;
import Deneme.SpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface BasketRepo extends CrudRepository<Basket, Long> {
  boolean  existsByProductId(Long productId);
    List<Basket> findByProductId(Long productId);

   @Query("select bs from Basket bs where bs.user.id= :userId")
    List<Basket> getbasketByUserId(@Param("userId") Long userId);

   List<Basket> findByUser(Long user);

   // asagidaki select cozulemedi hatasi veriyor.
    @Query(value = "select * from Basket bs where bs.user_id = :userId and bs.product_id = :productId",
            nativeQuery = true)
    List<Basket> getBasketByUserAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

//    @Query("select tr from Transaction tr where tr.account.id= :accountId and tr.date >= :date order by tr.date")
//    List<Transaction> getTransactionByAccountAndDate(@Param("accountId") Long accountId,@Param("date") Date date);


}
