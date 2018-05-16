package com.ch.common.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import com.ch.pojo.URLResource;
import com.ch.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;




@Service("mySecurityMetadataSource")
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
	//由spring调用
	Logger log = LogManager.getLogger(MySecurityMetadataSource.class);
	@Resource
	private LoginService dao;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	/*public MySecurityMetadataSource() {
		
		loadResourceDefine();
	}*/
	

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	//加载所有资源与权限的关系
	private void loadResourceDefine() {
		if(resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			/*List<String> resources ;
			resources = Lists.newArrayList("/jsp/user.do","/jsp/getoutput.do");*/
			List<URLResource> findResources = dao.findResource();
			
			for(URLResource url_resource:findResources){
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				ConfigAttribute configAttribute = new SecurityConfig(url_resource.getRole_Name());
				for(String resource:url_resource.getRole_url()){
					configAttributes.add(configAttribute);
					resourceMap.put(resource, configAttributes);
				}
			}
			//以权限名封装为Spring的security Object
		}
		Gson gson =new Gson();
		log.info("Correspondence between resource and permissions:"+gson.toJson(resourceMap));
		
		
		Set<Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap.entrySet();
		Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet.iterator();
		
	}
	//返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		log.info("requestUrl is " + requestUrl);
		if(resourceMap == null) {
			loadResourceDefine();
		}
		log.info("Permissions for resources:"+resourceMap.get(requestUrl));
		log.info(resourceMap.toString());
		return resourceMap.get(requestUrl);
	}

}
