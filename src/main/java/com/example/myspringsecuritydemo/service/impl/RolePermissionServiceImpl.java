package com.example.myspringsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myspringsecuritydemo.beans.RolePermission;
import com.example.myspringsecuritydemo.mapper.RolePermissionMapper;
import com.example.myspringsecuritydemo.service.RolePermissionService;
import org.springframework.stereotype.Service;

/**
* @author 86176
* @description 针对表【role_permission】的数据库操作Service实现
* @createDate 2024-01-05 20:57:33
*/
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService {

}




