package com.edrive.service;

import java.util.List;

//import javax.annotation.security.RolesAllowed;

import com.edrive.pojo.URLResource;
import com.edrive.pojo.Users;

public interface LoginService {

	boolean getinput();

	boolean geoutput();
	//@RolesAllowed("ROLE_ADMIN")//拥有ROLE_ADMIN权限的用户才可进入此方法
	boolean addreport_admin();
	//@RolesAllowed("ROLE_ADMIN")
	boolean deletereport_admin();

	// return user_id
	String getUserName();

	String getIdByName(String username);

	Users findByUsername(String name);
	//@RolesAllowed("ROLE_USER")
	void user_login();

	List<URLResource> findResource();

}
