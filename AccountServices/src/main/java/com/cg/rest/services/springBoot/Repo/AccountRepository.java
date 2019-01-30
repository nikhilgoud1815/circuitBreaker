package com.cg.rest.services.springBoot.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.rest.services.springBoot.Entity.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, Integer> {

}
