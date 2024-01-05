package com.example.myspringsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myspringsecuritydemo.beans.User;
import com.example.myspringsecuritydemo.mapper.UserMapper;
import com.example.myspringsecuritydemo.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author 86176
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-01-05 20:57:33
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




