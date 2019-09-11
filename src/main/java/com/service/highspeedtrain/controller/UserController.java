package com.service.highspeedtrain.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.service.highspeedtrain.data.User;
import com.service.highspeedtrain.exception.UserNotFoundException;
import com.service.highspeedtrain.service.UserService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class UserController {

	@Autowired
	private MessageSource messageSource; 
	
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userService.findAll();
	}

//	@GetMapping("/users/{id}")
//	public User retrieveUser(@PathVariable int id) {
//		User findUser = userService.findOne(id);
//
//		if (findUser == null)
//			throw new UserNotFoundException("id-" + id);
//
//		return findUser;
//	}

	
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = userService.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+ id);
		
		
		
		Resource<User> resource = new Resource<User>(user);
		
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	@PostMapping("/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User savedUser = userService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User deleteUser = userService.deleteById(id);
		if (deleteUser == null)
			throw new UserNotFoundException("id-" + id);

	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null, 
									LocaleContextHolder.getLocale());
	}

}
