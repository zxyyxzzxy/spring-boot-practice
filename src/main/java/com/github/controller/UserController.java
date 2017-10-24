package com.github.controller;

import com.github.mapper.UserMapper;
import com.github.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping
public class UserController {

	@Resource private UserMapper userMapper;
	@Resource private UserRepository userRepository;

	@GetMapping({"", "/", "index"})
	public String index() {

//		User user = new User();
//		user.setName("iMiracle");
//		user.setClassName("className");
//		userRepository.save(user);
//
//		List<User> userList = userRepository.findAll();
//		System.err.println(userList);
//
//		userList = userMapper.getAll();
//		System.err.println(userList);
//
//		userList = userMapper.getAllXml();
//		System.err.println(userList);

		return "index";
	}

}
