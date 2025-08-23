package com.logship.warehouse.service.repository;

import com.logship.warehouse.service.entity.ServingCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServingCodeRepository extends CrudRepository<ServingCode, Long> {

    boolean existsByPinCode(String pinCode);
}
