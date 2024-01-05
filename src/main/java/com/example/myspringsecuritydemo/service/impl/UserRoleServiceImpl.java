package com.example.myspringsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myspringsecuritydemo.beans.UserRole;
import com.example.myspringsecuritydemo.mapper.UserRoleMapper;
import com.example.myspringsecuritydemo.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
* @author 86176
* @description 针对表【user_role】的数据库操作Service实现
* @createDate 2024-01-05 20:57:33
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




