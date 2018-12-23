package com.example.common.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author perth
 * @ClassName JWTAuthenticationFilter
 * @Description TODO
 * @Date 2018/12/18 22:45
 * @Version 1.0
 **/
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private static final String mysecret="wenqiangwang";
    private static final int expiration=60;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication=null;
        Claims claims=null;
        String token = request.getHeader("Authorization");
        if (token != null) {
            try {
                 claims= Jwts.parser()
                        .setSigningKey(mysecret)
                        .parseClaimsJws(token.replace("Bearer ", ""))
                        .getBody();
                ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
                Map<String, Object> objectMap = (Map<String, Object>) claims.get("role");
                System.out.println(objectMap);
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(objectMap.get("authority").toString());
                simpleGrantedAuthorities.add(simpleGrantedAuthority);

                if (claims != null) {
                    authentication = new UsernamePasswordAuthenticationToken("", "", simpleGrantedAuthorities);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

       // UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String refreshedToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, mysecret)
                .compact();
        //response.setHeader("Access-Control-Expose-Headers","Authorization" );
        response.setHeader("Authorization", "Bearer " + refreshedToken);
        request.setAttribute("id",claims.get("id"));
        request.setAttribute("role",claims.get("role"));
        chain.doFilter(request, response);

    }

//    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        if (token != null) {
//            try {
//                Claims claims = Jwts.parser()
//                        .setSigningKey(mysecret)
//                        .parseClaimsJws(token.replace("Bearer ", ""))
//                        .getBody();
//                ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//                Map<String, Object> objectMap = (Map<String, Object>) claims.get("role");
//                System.out.println(objectMap);
//                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(objectMap.get("authority").toString());
//                simpleGrantedAuthorities.add(simpleGrantedAuthority);
//
//                if (claims != null) {
//                    return new UsernamePasswordAuthenticationToken(claims, "", simpleGrantedAuthorities);
//                }
//                return null;
//            }catch (Exception e){
//                System.out.println(e);
//            }
//        }
//        return null;
//    }

}