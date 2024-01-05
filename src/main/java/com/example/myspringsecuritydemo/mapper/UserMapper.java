package com.example.myspringsecuritydemo.mapper;

import com.example.myspringsecuritydemo.beans.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86176
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-01-05 20:57:33
* @Entity generator.beans.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




