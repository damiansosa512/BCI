package com.bci.cl.demo.repository;

import com.bci.cl.demo.entity.PhoneEntity;
import org.springframework.data.repository.CrudRepository;

public interface PhonesRepository extends CrudRepository<PhoneEntity, Long> {
}
