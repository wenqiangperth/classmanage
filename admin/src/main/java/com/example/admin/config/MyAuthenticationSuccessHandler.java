package com.example.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException, ServletException {
        try {
            JwtUser user = (JwtUser) jwtUserDetailsService.loadUserByUsername(authentication.getName());
            String token = jwtTokenUtils.generateToken(user);
            httpServletResponse.addHeader("Authorization", "Bearer " + token);
            httpServletResponse.setStatus(200);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
