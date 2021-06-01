package com.johnmendes.crudspringbootrestjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnmendes.crudspringbootrestjpa.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	

}
