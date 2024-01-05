package com.example.myspringsecuritydemo.security.jwtFilter;

import com.example.myspringsecuritydemo.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // 列出不需要JWT验证的路径
        List<String> excludePaths = Arrays.asList(
                "/login",
                "/register"
                // 其他排除的路径
        );

        // 检查当前请求路径是否在排除列表中，如果是login就直接放行
        if (excludePaths.contains(requestURI)) {
            chain.doFilter(request, response);
            return;
        }
        // 从HttpServletRequest中解析JWT
        String token = jwtTokenUtil.resolveToken(request);

        try {
            if (token != null && jwtTokenUtil.validateToken(token)) {
                // 重新登录获取认证信息
                Authentication auth = jwtTokenUtil.getAuthentication(token);

                if (auth != null) {
                    // 设置最新的用户信息和权限到SecurityContextHolder
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("token空");
                response.getWriter().flush();
                return;

            }
        } catch (Exception e) {
            // 处理token验证失败的情况，例如JwtException
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("token验证失败");
            response.getWriter().flush();
            return;
        }

        chain.doFilter(request, response);

    }
}
