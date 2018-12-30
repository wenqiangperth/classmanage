package com.example.common.config;

import com.example.common.config.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author perth
 * @ClassName JwtTokenUtils
 * @Description TODO
 * @Date 2018/12/18 22:37
 * @Version 1.0
 **/
@Component
public class JwtTokenUtils {
    private static final String MYSECRET="wenqiangwang";
    private static final int EXPIRATION=60000;


    public String generateToken(JwtUser user){
        Map<String,Object> claims=new HashMap<>();
        claims.put("sub",user.getUsername());
        claims.put("id",user.getId());
        claims.put("role",user.getAuthorities().toArray()[0]);
        String token= Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .signWith(SignatureAlgorithm.HS512, MYSECRET)
                .compact();
        return token;
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            refreshedToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                    .signWith(SignatureAlgorithm.HS512, MYSECRET)
                    .compact();
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public String getUserNameFromToken(String token){
        String username="";
        try{
            username =getClaimsFromToken(token).getSubject();
        }catch (Exception e){
            username=null;
        }
        return username;
    }

    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(MYSECRET)
                    .parseClaimsJws(token.replace("Bearer ",""))
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }


}
