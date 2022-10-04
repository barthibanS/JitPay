package com.jit.pay.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jit.pay.entity.UserDetails;

@Repository
public interface UserDetailsRepo  extends CrudRepository<UserDetails, String> {

}
