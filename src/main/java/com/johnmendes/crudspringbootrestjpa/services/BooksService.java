package com.johnmendes.crudspringbootrestjpa.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.johnmendes.crudspringbootrestjpa.entity.Book;
import com.johnmendes.crudspringbootrestjpa.entity.Comment;
import com.johnmendes.crudspringbootrestjpa.exceptions.BookNotFoundException;
import com.johnmendes.crudspringbootrestjpa.repository.BooksRepository;
import com.johnmendes.crudspringbootrestjpa.repository.CommentsRepository;

@Service
public class BooksService {

	@Autowired
	private BooksRepository booksRepository;

	@Autowired
	private CommentsRepository commentsRepository;
	
	public List<Book> list(){
		return booksRepository.findAll();
	}
	
	public List<Book> find(Long id){
		@SuppressWarnings("unchecked")
		List<Book> book = (List<Book>) booksRepository.findById(id).orElse(new Book());
		
		if(book == null){
			throw new BookNotFoundException("The Book was not found");
		}
		return book;
	}
	
	public Book save(Book book){
		book.setId(null);
		return booksRepository.save(book);
	}
	
	public void delete(Long id){
		try {
			booksRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e){
			throw new BookNotFoundException("Book not found");
		}
	}
	
	public void update(Book book){
		checkIfExist(book);
		booksRepository.save(book);
	}

	private void checkIfExist(Book book) {
		find(book.getId());		
	}
	
	public Comment saveComment(Long bookId, Comment comment){
		Book book = (Book) find(bookId);
		
		comment.setBook(book);
		comment.setDate(new Date());
		
		return commentsRepository.save(comment);
	}
	
	public List<Comment> listComment(Long bookId){
		List<Book> book = find(bookId);
	
		return ((Book) book).getComments();
	}
}
