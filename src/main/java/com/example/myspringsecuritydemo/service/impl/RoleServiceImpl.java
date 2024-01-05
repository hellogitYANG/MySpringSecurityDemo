package com.example.myspringsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myspringsecuritydemo.beans.Role;
import com.example.myspringsecuritydemo.mapper.RoleMapper;
import com.example.myspringsecuritydemo.service.RoleService;
import org.springframework.stereotype.Service;

/**
* @author 86176
* @description 针对表【role】的数据库操作Service实现
* @createDate 2024-01-05 20:57:33
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

}




