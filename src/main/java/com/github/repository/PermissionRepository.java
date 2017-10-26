package com.github.repository;

import com.github.model.Permission;
import com.github.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {


    @Query(value = "SELECT DISTINCT(t.id), t.* FROM t_permission t, t_role_permission p, t_role r, t_user_role u " +
            "WHERE p.permission_id = t.id AND p.role_id = r.id AND r.id = u.role_id AND t.`status` = 1 AND t.parent_id = 0 AND u.user_id = ? ORDER BY t.id ASC", nativeQuery = true)
    List<Permission> getUserPermissionTree(Integer userId);
}

