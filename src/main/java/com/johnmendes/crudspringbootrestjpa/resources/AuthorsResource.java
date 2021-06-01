package com.johnmendes.crudspringbootrestjpa.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.johnmendes.crudspringbootrestjpa.entity.Author;
import com.johnmendes.crudspringbootrestjpa.services.AuthorsService;

@RestController
@RequestMapping("/authors")
public class AuthorsResource {

	@Autowired
	private AuthorsService authorsService;
	//MediaType é usado para definir qual o formato de saida é permitido, neste caso Json e XML
	@RequestMapping(method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Author>> List(){
		return ResponseEntity.status(HttpStatus.OK).body(authorsService.list());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Author author){
		author = authorsService.save(author);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(author.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Author>> find(@PathVariable ("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(authorsService.find(id));
	}
}
