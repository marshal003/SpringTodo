package com.hashedin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hashedin.entity.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

}
