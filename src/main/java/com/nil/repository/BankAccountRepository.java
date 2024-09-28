package com.nil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nil.entity.BankAccount;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Long>{

	@Query("select c from BankAccount c where c.user.mobileNumber= :mobileNumber")
	List<BankAccount> findByMobileNumber(@Param("mobileNumber") Long mobileNumber);
}
