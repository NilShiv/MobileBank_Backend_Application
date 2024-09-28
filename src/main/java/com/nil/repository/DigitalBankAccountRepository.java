package com.nil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nil.entity.DigitalBankAccount;

@Repository
public interface DigitalBankAccountRepository extends CrudRepository<DigitalBankAccount, String>{

	@Query("select c from DigitalBankAccount c where c.bankAccount.accountNumber= :accountNumber")
	Optional<DigitalBankAccount> findByAccountNumber(@Param("accountNumber") Long accountNo);
	
	@Query("select c.digitalBankingId from DigitalBankAccount c order by c.digitalBankingId desc limit 1")
	String findLatestId();
	
	@Query("select c from DigitalBankAccount c where c.user.mobileNumber= :mobileNumber")
	Optional<DigitalBankAccount> findByMobileNumber(@Param("mobileNumber") Long mobileNo);
	
	@Query("select c from DigitalBankAccount c where c.user.mobileNumber= ?1 and c.bankAccount.accountNumber= ?2")
	DigitalBankAccount findByMobileNoAndAccountNo(Long mobileNo, Long accountNo);
}
