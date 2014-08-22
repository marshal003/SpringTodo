package com.hashedin.sample.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hashedin.sample.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findByLastName(String lastName); 
}
