package com.edrive.common.context;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edrive.service.LoginService;
import com.edrive.pojo.Users;
/**
 * 在spring-security.xml中如果配置了
 * <authentication-manager>
 <authentication-provider user-service-ref="myUserDetailsService" />
 </authentication-manager>
 * 将会使用这个类进行权限的验证。
 *
 * **/
@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService{
    @Resource
    private LoginService loginService;

    //登录验证
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {
        System.out.println("show login name："+name+" ");
        Users users =loginService.findByUsername(name);
        Set<GrantedAuthority> grantedAuths=obtionGrantedAuthorities(users);

        boolean enables = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        //封装成spring security的user
        User userdetail = new User(users.getName(), users.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
        return userdetail;
    }
    //查找用户权限
    public Set<GrantedAuthority> obtionGrantedAuthorities(Users users){
        String roles[] = users.getRole().split(",");
        Set<GrantedAuthority> authSet=new HashSet<GrantedAuthority>();
        for (int i = 0; i < roles.length; i++) {
            authSet.add(new SimpleGrantedAuthority(roles[i]));
        }
        return authSet;
    }

}
