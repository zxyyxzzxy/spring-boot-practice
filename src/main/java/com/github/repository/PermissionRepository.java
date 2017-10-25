package com.github.repository;

import com.github.model.Permission;
import com.github.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    @Query(value = "SELECT t.* FROM t_permission t, t_role_permission p " +
            "WHERE t.status = 1 AND p.permission_id = t.id AND p.role_id = ?", nativeQuery = true)
    List<Permission> getRolePermissionList(Integer roleId);
}
