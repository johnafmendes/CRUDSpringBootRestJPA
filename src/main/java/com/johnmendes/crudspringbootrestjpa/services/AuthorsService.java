package com.johnmendes.crudspringbootrestjpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnmendes.crudspringbootrestjpa.entity.Author;
import com.johnmendes.crudspringbootrestjpa.exceptions.AuthorExistException;
import com.johnmendes.crudspringbootrestjpa.exceptions.AuthorNotFoundException;
import com.johnmendes.crudspringbootrestjpa.repository.AuthorRepository;

@Service
public class AuthorsService {

	@Autowired
	private AuthorRepository authorRepository;
	
	public List<Author> list(){
		return authorRepository.findAll();
	}
	
	public Author save(Author author){
		if(author.getId() != null){
			Optional<Author> a = authorRepository.findById(author.getId());
			
			if(a != null){
				throw new AuthorExistException("Author exist already.");
			}
		}
		return authorRepository.save(author);
	}
	
	public Optional<Author> find(Long id){
		Optional<Author> author = authorRepository.findById(id);
		
		if(author == null){
			throw new AuthorNotFoundException("Author not found");
		}
		return author;
	}
}
