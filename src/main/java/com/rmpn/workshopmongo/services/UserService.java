package com.rmpn.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmpn.workshopmongo.domain.User;
import com.rmpn.workshopmongo.repository.UserRepository;
import com.rmpn.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	
	
    public List<User> findAll() {
		return repo.findAll();
    	
    }
    
    public User findById(String id) {
    	
    	if (!repo.existsById(id)) {
    		throw new ObjectNotFoundException("Objeto não encontrado");
    	}
    	
    	
    	
    	return repo.findById(id).get();
    	
    	
	}

}
