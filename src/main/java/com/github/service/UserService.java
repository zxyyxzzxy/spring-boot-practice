package com.github.service;

import com.github.model.Permission;
import com.github.model.Role;
import com.github.model.User;
import com.github.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

	@Resource private UserRepository userRepository;


	public Set<Permission> getUserPermissionSet(Integer userId) {

		User user = userRepository.getOne(userId);

		Set<Permission> permissionSet = new HashSet<Permission>();
		for (Role role : user.getRoleSet()) {
			permissionSet.addAll(role.getPermissionSet());
		}

		return permissionSet;
	}

}
