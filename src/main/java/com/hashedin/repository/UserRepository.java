package com.hashedin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hashedin.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public User findByEmail(String email);
}
