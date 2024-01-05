package com.example.myspringsecuritydemo.mapper;

import com.example.myspringsecuritydemo.beans.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86176
* @description 针对表【role】的数据库操作Mapper
* @createDate 2024-01-05 20:57:33
* @Entity generator.beans.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}




