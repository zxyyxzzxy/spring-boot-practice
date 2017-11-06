package com.github.controller;

import com.github.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class LoginController {

	@Resource private UserService userService;

	@GetMapping({"", "/", "login"})
	public String login() {

		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return UrlBasedViewResolver.FORWARD_URL_PREFIX + "/index";
		}

		return "login";
	}

	@PostMapping({"", "/", "login"})
	public String login(HttpServletRequest request, Model model) {

		// 认证未通过或反复POST认真进入这里
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return UrlBasedViewResolver.FORWARD_URL_PREFIX + "/index";
		}

		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");

		String errorMessage = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			errorMessage = "用户名或密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			errorMessage = "用户名或密码错误";
		} else if (exceptionClassName != null) {
			errorMessage = "其他错误：" + exceptionClassName;
		}

		model.addAttribute("errorMessage", errorMessage);
		return "login";
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return UrlBasedViewResolver.FORWARD_URL_PREFIX + "/login";
	}

	@GetMapping("index")
	public String index(Model model) {

		model.addAttribute("userMenuPermissionList", userService.getUserMenuPermissionList(1));
		return "index";
	}

}
