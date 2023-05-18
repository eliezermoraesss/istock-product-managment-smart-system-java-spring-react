package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
