package com.github.repository;

import com.github.model.Role;
import com.github.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {



}
