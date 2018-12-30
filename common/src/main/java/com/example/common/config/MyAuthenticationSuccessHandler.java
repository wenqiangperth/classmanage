package com.example.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author perth
 * @ClassName MyAuthenticationSuccessHandler
 * @Description TODO
 * @Date 2018/12/18 22:31
 * @Version 1.0
 **/
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException, ServletException {
        System.out.println("登陆验证成功");
        try {
            JwtUser user = (JwtUser) jwtUserDetailsServiceImpl.loadUserByUsername(authentication.getName());
            String token = jwtTokenUtils.generateToken(user);
            httpServletResponse.setHeader("Access-Control-Expose-Headers","Authorization" );
            httpServletResponse.setHeader("Authorization", "Bearer " + token);

            Map<String, Object> maps = new HashMap<String, Object>();
            maps.put("role", user.getAuthorities().toArray()[0]);
            maps.put("isActive", user.getIsActive());
            httpServletResponse.getWriter().write(maps.toString());
            httpServletResponse.setStatus(200);


        }catch (Exception e){
            System.out.println(e);
        }

    }
}
