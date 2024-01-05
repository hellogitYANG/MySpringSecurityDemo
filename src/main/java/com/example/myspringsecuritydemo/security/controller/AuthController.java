package com.example.myspringsecuritydemo.security.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.myspringsecuritydemo.beans.User;
import com.example.myspringsecuritydemo.mapper.UserMapper;
import com.example.myspringsecuritydemo.security.jwt.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AuthController  extends ApiController {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private UserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public R login(@RequestBody User user) {
        try {
            // 使用Spring Security的AuthenticationManager进行身份验证
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            // 如果身份验证成功，生成JWT令牌
            String token = jwtTokenUtil.createToken(user.getUsername());

            // 返回JWT令牌给前端
            return success(token);
        } catch (AuthenticationException e) {
            // 处理身份验证失败的情况
            return failed("身份验证失败");
        }
    }

    @PostMapping("/register")
    public R register(@RequestBody User user) {
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty()){
            return failed("请输入账号密码");
        }

        User oldUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if(oldUser!=null){
            return failed("该用户已注册");
        }else {
            // 使用BCryptPasswordEncoder加密密码
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
            int insert = userMapper.insert(user);
            if(insert>0){
                return success("注册成功");
            }else {
                return success("未知错误,注册失败");
            }
        }

    }
}