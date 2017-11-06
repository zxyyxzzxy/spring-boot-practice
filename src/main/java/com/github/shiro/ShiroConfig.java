package com.github.shiro;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class ShiroConfig {

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*"); 
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
	}
	
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);
		bean.setLoginUrl("/login");
		bean.setSuccessUrl("/index");
		bean.setUnauthorizedUrl("/unauthorize");
		
		Map<String, Filter> filters = new HashMap<String, Filter>();
//		filters.put("perms", urlPermissionsFilter());
		bean.setFilters(filters);
		
		Map<String, String> chains = new HashMap<String, String>();
		chains.put("/assets/**", "anon");
		chains.put("/logout", "logout");
		chains.put("/**", "authc");
		bean.setFilterChainDefinitionMap(chains);
		return bean;
	}

	@Bean(name="securityManager")
	public DefaultWebSecurityManager securityManager(CacheManager shiroCacheManager, AdminRealm adminRealm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(adminRealm);
		manager.setCacheManager(shiroCacheManager);
		return manager;
	}
	
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public AdminRealm adminRealm(CacheManager shiroCacheManager, CredentialsMatcher credentialsMatcher) {
		AdminRealm adminRealm = new AdminRealm();
		adminRealm.setCacheManager(shiroCacheManager);
		adminRealm.setCredentialsMatcher(credentialsMatcher);
		return adminRealm;
	}
	
	@Bean
	public URLPermissionsFilter urlPermissionsFilter() {
		return new URLPermissionsFilter();
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn("cacheManager")
	public CacheManager shiroCacheManager(net.sf.ehcache.CacheManager cacheManager) {
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManager(cacheManager);
		return ehCacheManager;
	}
}