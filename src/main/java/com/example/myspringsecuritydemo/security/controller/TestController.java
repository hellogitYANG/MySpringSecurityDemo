package com.example.myspringsecuritydemo.security.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myspringsecuritydemo.beans.Role;
import com.example.myspringsecuritydemo.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("text")
public class TestController extends ApiController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public R selectAll() {
        //获取上下文信息方法
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("刚刚登录用户的用户名为："+authentication1.getName());
        return success("你拥有权限");
    }


}

