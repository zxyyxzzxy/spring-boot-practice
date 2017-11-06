package com.github.controller;

import com.github.service.UserService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LoginController {

	@Resource private UserService userService;

	@GetMapping({"", "/", "login"})
	public String login() {
		return "login";
	}

	@PostMapping({"", "/", "login"})
	public String login(HttpServletRequest request, Model model) {
		// 只有认证未通过才进入这里

		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		System.err.println(exceptionClassName);

		String errorMessage = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			errorMessage = "用户名/密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			errorMessage = "用户名/密码错误";
		} else if (exceptionClassName != null) {
			errorMessage = "其他错误：" + exceptionClassName;
		}

		model.addAttribute("errorMessage", errorMessage);
		return "login";
	}

	@GetMapping("index")
	public String index(Model model) {

		model.addAttribute("userMenuPermissionList", userService.getUserMenuPermissionList(1));
		return "index";
	}

}
