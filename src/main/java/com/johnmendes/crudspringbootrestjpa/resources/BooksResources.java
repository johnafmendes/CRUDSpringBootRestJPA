package com.johnmendes.crudspringbootrestjpa.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.johnmendes.crudspringbootrestjpa.entity.Book;
import com.johnmendes.crudspringbootrestjpa.entity.Comment;
import com.johnmendes.crudspringbootrestjpa.services.BooksService;

@RestController
@RequestMapping (value = "/books")
public class BooksResources {
	
	@Autowired
	private BooksService booksService;
	
	@CrossOrigin //permite acesso de domínios de qualquer lugar
	@RequestMapping (method = RequestMethod.GET)
	public ResponseEntity<List<Book>> list(){
//		Livro l1 = new Livro("Rest aplicado");
//		Livro l2 = new Livro("Git passo a passo");
//		
//		Livro[] livros = {l1, l2};
//		return Arrays.asList(livros);
		return ResponseEntity.status(HttpStatus.OK).body(booksService.list());
	}
	
	@RequestMapping (method = RequestMethod.POST)
	//o @Valid é a maneira de obrigar a validar os campos de entrada definido 
	//nas classes livro, comentario e autor
	public ResponseEntity<Void> save(@Valid @RequestBody Book book){
		book = booksService.save(book);
		
		//devolve para o location no header
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(book.getId()).toUri();
		
		//retorna um 201 com o location no header indicando como faz para encontrar o recurso criado
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping (value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable("id") Long id){
		List<Book> book = booksService.find(id);
		
		CacheControl cache = CacheControl.maxAge(30, TimeUnit.SECONDS);
		/*try{
			livro = livrosService.buscar(id);
		} catch(LivroNaoEncontradoException e){
			return ResponseEntity.notFound().build();//retorna erro 404 de not found.
		}*/
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cache).body(book); 
	}
	
	@RequestMapping (value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		booksService.delete(id);
		/*try{
			livrosService.deletar(id);
		} catch (LivroNaoEncontradoException e){
			//caso nao consiga deletar um registro por ele nao existir
			//há uma exceção e retorna o 404 de not found em vez de erro 500.
			return ResponseEntity.notFound().build();
		}*/
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping (value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update (@RequestBody Book book, @PathVariable("id") Long id){
		book.setId(id);
		booksService.update(book);
		/*try {
			livrosService.atualizar(livro);
		} catch (LivroNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}*/
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping (value = "/{id}/comments", method = RequestMethod.POST)
	public ResponseEntity<Void> addComment(@PathVariable("id") Long bookId, 
			@RequestBody Comment comment){
		booksService.saveComment(bookId, comment);
		
		//usado para pegar o contexto de quem esta acessando a aplicacao atraves do usuario e senha
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//comment.setUser(auth.getName());
		comment.setUser("John");
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping (value = "/{id}/comments", method = RequestMethod.GET)
	public ResponseEntity<List<Comment>> listComment(@PathVariable("id") Long bookId){
		List<Comment> comments = booksService.listComment(bookId);
		
		return ResponseEntity.status(HttpStatus.OK).body(comments);
	}
}
