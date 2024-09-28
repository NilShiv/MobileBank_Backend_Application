package com.nil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nil.entity.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{

	@Query("select c from Transaction c where c.paidForm= :mobileNo")
	List<Transaction> findByPaidFrom(@Param("mobileNo") Long mobileNo);
}
