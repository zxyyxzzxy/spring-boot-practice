package com.github.controller;

import com.github.mapper.UserMapper;
import com.github.model.User;
import com.github.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping
public class UserController {

	@Resource private UserMapper userMapper;
	@Resource private UserRepository userRepository;

	@GetMapping({"", "/"})
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

	@ResponseBody
	@GetMapping("demo")
	public List<User> demo() {

		List<User> userList = userMapper.getAll();

		return userList;
	}

}
