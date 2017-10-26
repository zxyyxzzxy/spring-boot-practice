package com.github.controller;

import com.github.mapper.UserMapper;
import com.github.repository.PermissionRepository;
import com.github.repository.UserRepository;
import com.github.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping
public class LoginController {

	@Resource private UserService userService;
	@Resource private UserRepository userRepository;
	@Resource private PermissionRepository permissionRepository;

	@GetMapping({"", "/", "login"})
	public String login() {
		return "login";
	}

	@GetMapping("index")
	public String index(Model model) {

		model.addAttribute("permissionSet", permissionRepository.getUserPermissionTree(1));
		return "index";
	}

}
