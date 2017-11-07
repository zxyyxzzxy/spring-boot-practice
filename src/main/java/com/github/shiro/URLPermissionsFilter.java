package com.github.shiro;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.github.mapper.UserMapper;
import com.github.model.User;
import com.github.service.PermissionService;
import com.github.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class URLPermissionsFilter extends PermissionsAuthorizationFilter{

	@Resource private UserMapper userMapper;
	@Resource private PermissionService permissionService;

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

		String url = getRequestUrl(request);

		if(StringUtils.endsWithAny(url, ".js",".css",".html")
				|| StringUtils.endsWithAny(url, ".jpg",".png",".gif", ".jpeg")
				|| StringUtils.equals(url, "/unauthorize")) {
			return true;
		}

		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		User user = userMapper.getByUserName(userName);
		Set<String> urlSet = permissionService.getUserPermissionStringSet(user.getId());

		return urlSet.contains(url);
	}

	
	private String getRequestUrl(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest)request;
		String queryString = req.getQueryString();

		queryString = StringUtils.isBlank(queryString)? "" : "?" + queryString;
		return req.getRequestURI() + queryString;
	}
}
