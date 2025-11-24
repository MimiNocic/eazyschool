package com.eng.it.eazybytes.eazyschool.repository;

import com.eng.it.eazybytes.eazyschool.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Address, Integer> {
}
