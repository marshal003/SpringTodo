package com.hashedin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hashedin.entity.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

}
