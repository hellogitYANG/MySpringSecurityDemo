package com.example.myspringsecuritydemo.security.config;

import com.example.myspringsecuritydemo.security.service.MyUserDetailsService;
import com.example.myspringsecuritydemo.security.jwtFilter.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启方法级注释
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;  // 注入自定义的用户详情服务
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭跨站请求伪造保护
                .csrf().disable()
                // 配置请求授权
                .authorizeRequests()
                // 允许所有用户访问登录注册接口，但是仍然要走jwt的拦截器，因为Jwt拦截器是addFilterBefore的，需要在jwt拦截器单独做放行
                .antMatchers("/login","/register")
                .permitAll()
                // 其他所有请求都需要认证
                .anyRequest().authenticated()
                .and()
                // 禁用默认登录页面
                .formLogin().disable()
                // 禁用HTTP Basic认证
                .httpBasic().disable()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // 向外抛出bean，以便其他地方使用AuthenticationManager进行身份验证，比如/login的时候就需要调用身份认证
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)  // 使用自定义的用户详情服务，里面loadUserByUsername会在你/login鉴权的时候执行
                                                        //先查出用户信息和权限信息，然后再比对使用特定编码器对比密码，如果成功添加至上下文
                                                        // /login就不会抛出异常，就成功登录，返回token
            .passwordEncoder(passwordEncoder());  // 使用密码编码器
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // 定义密码编码器为BCryptPasswordEncoder
    }
}