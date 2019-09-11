package com.service.highspeedtrain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.service.highspeedtrain.data.User;

@Component
public class UserService {

	private static List<User> users = new ArrayList<>();

	private static int usersCount = 3;

	static {
		users.add(new User(1, "Adam", new Date(), new ArrayList<>()));
		users.add(new User(2, "Eve", new Date(), new ArrayList<>()));
		users.add(new User(3, "Jack", new Date(), new ArrayList<>()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getId() == null)
			user.setId(++usersCount);
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		return users.stream().filter(p -> id == p.getId()).findFirst().orElse(null);
	}

	public User deleteById(int id) {
		User findUser = users.stream().filter(p -> id == p.getId()).findFirst().orElse(null);
		if (findUser != null)
			users.remove(findUser);
		return findUser;
	}
}
