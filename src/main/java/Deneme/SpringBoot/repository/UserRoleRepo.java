package Deneme.SpringBoot.repository;

import Deneme.SpringBoot.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepo extends JpaRepository<UserRole, Long> {

}
