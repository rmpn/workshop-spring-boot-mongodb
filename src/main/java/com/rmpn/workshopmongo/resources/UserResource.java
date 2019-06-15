package com.rmpn.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rmpn.workshopmongo.domain.User;
import com.rmpn.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	/* @GetMapping* => também funciona */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		
		/*
		User maria = new User("1", "Maria Vitória", "vitoria@gmail.com");
		User pedro = new User("2", "Jose Pedro", "pedro@gmail.com");
		List<User> list = new ArrayList<>();
		list.add(maria);
		list.add(pedro);
		*/
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
		
	}
	

}
