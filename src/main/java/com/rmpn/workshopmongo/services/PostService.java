package com.rmpn.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmpn.workshopmongo.domain.Post;
import com.rmpn.workshopmongo.domain.User;
import com.rmpn.workshopmongo.dto.UserDTO;
import com.rmpn.workshopmongo.repository.PostRepository;
import com.rmpn.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public List<Post> findAll() {
		return repo.findAll();

	}

	public Post findById(String id) {

		if (!repo.existsById(id)) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}

		return repo.findById(id).get();

	}

	public List<Post> findByTitle(String text) {

		//return repo.findByTitleContainingIgnoreCase(text);
		return repo.searchTitle(text);
	}

	public Post insert(Post obj) {

		return repo.insert(obj);

	}

	public void delete(Post obj) {

		findById(obj.getId());

		repo.delete(obj);

	}

	public Post update(Post obj) {

		Post newPost = repo.findById(obj.getId()).get();
		updateData(obj, newPost);

		repo.save(newPost);

		return newPost;

	}

	private void updateData(Post obj, Post newPost) {

		newPost.setTitle(obj.getTitle());
		newPost.setBody(obj.getBody());
		newPost.setDate(obj.getDate());
		newPost.setAuthor(obj.getAuthor());
	}

	public User fromDTO(UserDTO objDTO) {

		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}

}
