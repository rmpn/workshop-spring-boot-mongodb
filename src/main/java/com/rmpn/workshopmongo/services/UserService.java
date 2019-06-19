package com.rmpn.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmpn.workshopmongo.domain.User;
import com.rmpn.workshopmongo.dto.UserDTO;
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

	public User insert(User obj) {

		return repo.insert(obj);

	}

	public void delete(User obj) {

		findById(obj.getId());

		repo.delete(obj);

	}

	public User update(User obj) {

		User newUser = repo.findById(obj.getId()).get();
		updateData(obj, newUser);

		repo.save(newUser);

		return newUser;

	}

	private void updateData(User obj, User newUser) {

		newUser.setName(obj.getName());
		newUser.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDTO) {

		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}

}
