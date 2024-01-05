package com.example.myspringsecuritydemo.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myspringsecuritydemo.beans.Role;
import com.example.myspringsecuritydemo.beans.User;
import com.example.myspringsecuritydemo.beans.UserRole;
import com.example.myspringsecuritydemo.mapper.RoleMapper;
import com.example.myspringsecuritydemo.mapper.UserMapper;
import com.example.myspringsecuritydemo.mapper.UserRoleMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username",username));
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");  // 如果用户不存在，抛出异常
        }
        //获取用户对应的角色关系
        List<UserRole> userRoles = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
        ArrayList<Role> roles = new ArrayList<>();
        //遍历关系的角色ID查询出角色LIST
        for (UserRole userRole : userRoles) {
            Role role = roleMapper.selectById(userRole.getRoleId());
            roles.add(role);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role :roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }

        // 返回Spring Security UserDetails对象，包含用户名、密码和权限集合
        // 供security进行认证，存储的话你需要在外部手动loadUserByUsername后返回集合，然后new UsernamePasswordAuthenticationToken进行放入上下文
         return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
        

}


