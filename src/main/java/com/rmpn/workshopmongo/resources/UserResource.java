package com.rmpn.workshopmongo.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rmpn.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	/* @GetMapping* => também funciona */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		
		User maria = new User("1", "Maria Vitória", "vitoria@gmail.com");
		User pedro = new User("2", "Jose Pedro", "pedro@gmail.com");
		List<User> list = new ArrayList<>();
		list.add(maria);
		list.add(pedro);
		return ResponseEntity.ok().body(list);
		
		
	}
	

}
