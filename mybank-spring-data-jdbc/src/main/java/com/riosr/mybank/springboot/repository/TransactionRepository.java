package com.riosr.mybank.springboot.repository;

import com.riosr.mybank.springboot.model.Transaction;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

    @Query("select * from \"transactions\" where receiving_user = :userId")
    public Iterable<Transaction> findByUserId(@Param("userId") String userId);
}
