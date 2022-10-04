package com.jit.pay.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jit.pay.entity.LocationDetails;

@Repository
public interface LocationDetailsRepo  extends CrudRepository<LocationDetails, Long> {

}
