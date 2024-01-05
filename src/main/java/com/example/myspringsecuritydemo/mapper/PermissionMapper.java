package com.example.myspringsecuritydemo.mapper;

import com.example.myspringsecuritydemo.beans.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86176
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2024-01-05 20:57:33
* @Entity generator.beans.Permission
*/
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}




