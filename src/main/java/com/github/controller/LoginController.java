package com.github.controller;

import com.github.mapper.UserMapper;
import com.github.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("fsdf")
public class LoginController {

	@Resource private UserMapper userMapper;
	@Resource private UserRepository userRepository;

	@GetMapping({"", "/", "login"})
	public String index() {
		return "login";
	}

}
