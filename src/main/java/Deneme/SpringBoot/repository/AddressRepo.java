package Deneme.SpringBoot.repository;

import Deneme.SpringBoot.model.Address;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepo extends CrudRepository<Address, Long> {
}
