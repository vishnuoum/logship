package com.logship.warehouse.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServingCodeRepository extends CrudRepository<ServingCodeRepository, Long> {

    boolean existsByPinCode(Integer pinCode);
}
