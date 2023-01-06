package Deneme.SpringBoot.repository;

import Deneme.SpringBoot.model.BasketGuest;
import Deneme.SpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketGuestRepo extends JpaRepository<BasketGuest,Long> {


}
