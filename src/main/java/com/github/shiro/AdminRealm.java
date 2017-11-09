package com.github.shiro;

import com.github.mapper.UserMapper;
import com.github.model.User;
import com.github.service.PermissionService;
import com.github.service.RoleService;
import com.github.util.Constants;
import com.github.util.SpringContextHolder;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

public class AdminRealm extends AuthorizingRealm {

    @Resource private UserMapper userMapper;
    @Resource private RoleService roleService;
    @Resource private PermissionService permissionService;

    public AdminRealm() {
        super();
        super.setCachingEnabled(false);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
    	return token instanceof UsernamePasswordToken;
    }
    
    /** 认证 */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String userName = (String) token.getPrincipal();
        User user = userMapper.getByUserName(userName);
		SpringContextHolder.getSession().setAttribute(Constants.SESSION_CURRENT_USER, user);

        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if(Constants.STATUS_FALSE.equals(user.getStatus())) {
            throw new LockedAccountException(); //帐号锁定
        }
        
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配, 如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), super.getName());
        return authenticationInfo;
    }
    
    /** 授权 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String userName = (String) principals.getPrimaryPrincipal();
        User user = userMapper.getByUserName(userName);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleService.getUserRoleStringSet(user.getId()));
        authorizationInfo.setStringPermissions(permissionService.getUserPermissionStringSet(user.getId()));
        
        return authorizationInfo;
    }
}
