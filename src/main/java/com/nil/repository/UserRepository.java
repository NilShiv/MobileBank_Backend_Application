package com.nil.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nil.entity.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	List<User> findByPassword(String password);
	Optional<User> findByUserId(String userId);
	
}
