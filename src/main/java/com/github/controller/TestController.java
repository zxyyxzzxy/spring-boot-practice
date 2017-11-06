package com.github.controller;

import com.github.mapper.PermissionMapper;
import com.github.mapper.RoleMapper;
import com.github.model.Permission;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("t")
public class TestController {

	@Resource private RoleMapper roleMapper;
	@Resource private PermissionMapper permissionMapper;

	@ResponseBody
	@GetMapping({"", "/"})
	public Object index() {

		return permissionMapper.getUserPermissionSet(1).size();
	}

}
