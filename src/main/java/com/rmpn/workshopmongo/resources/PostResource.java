package com.rmpn.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rmpn.workshopmongo.domain.Post;
import com.rmpn.workshopmongo.resources.util.URL;
import com.rmpn.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

//	/* @GetMapping* => também funciona */
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<List<UserDTO>> findAll() {
//
//		List<User> list = service.findAll();
//		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
//		return ResponseEntity.ok().body(listDTO);
//
//	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {

		Post obj = service.findById(id);

		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text",defaultValue = "") String text) {

		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);

	}
	
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text",defaultValue = "") String text,
			@RequestParam(value="minDate",defaultValue = "") String minDate,
			@RequestParam(value="maxDate",defaultValue = "") String maxDate
			) {
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
		text = URL.decodeParam(text);
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);

	}
	
	
	
//	// @PostMapping
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO) {
//
//		User obj = service.fromDTO(objDTO);
//		obj = service.insert(obj);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
//		return ResponseEntity.created(uri).build();
//
//	}

//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<Void> delete(@PathVariable String id) {
//
//		User obj = service.findById(id);
//		service.delete(obj);
//		return ResponseEntity.noContent().build();
//
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id) {
//
//		User obj = service.fromDTO(objDTO);
//		obj.setId(id);
//		obj = service.update(obj);
//		return ResponseEntity.noContent().build();
//
//	}
	
//	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
//	public ResponseEntity<List<Post>> findPost(@PathVariable String id) {
//
//		User obj = service.findById(id);
//
//		return ResponseEntity.ok().body(obj.getPost());
//
//	}

}
