package com.johnmendes.crudspringbootrestjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnmendes.crudspringbootrestjpa.entity.Comment;

public interface CommentsRepository extends JpaRepository<Comment, Long>{

}
