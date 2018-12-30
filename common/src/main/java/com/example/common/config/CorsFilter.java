package com.example.common.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author perth
 * @ClassName CorsFilter
 * @Description 处理跨域等问题
 * @Date 2018/12/18 22:11
 * @Version 1.0
 **/
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)//控制过滤器的级别
public class CorsFilter implements Filter {

    final static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CorsFilter.class);


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        HttpServletRequest reqs = (HttpServletRequest) req;


        response.setHeader("Access-Control-Allow-Origin",reqs.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT,PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Origin, X-Requested-With,Accept,XFILENAME,XFILECATEGORY,XFILESIZE,Authorization");
       // response.setHeader("Access-Control-Allow-Headers","*");
        response.setHeader("Access-Control-Allow-Credentials","true");
        //是否允许请求带有验证信息


//        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
//            System.out.println("options");
        if(((HttpServletRequest)req).getMethod().equals(RequestMethod.OPTIONS.name())){
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
        //chain.doFilter(req, res);
    }
    @Override
    public void init(FilterConfig filterConfig) {}
    @Override
    public void destroy() {}
}
