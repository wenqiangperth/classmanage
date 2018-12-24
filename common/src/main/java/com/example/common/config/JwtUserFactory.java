package com.example.common.config;

import com.example.common.config.JwtUser;
import com.example.common.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @author perth
 * @ClassName JwtUser
 * @Description 生成JwtUser
 * @Date 2018/12/18 22:17
 * @Version 1.0
 **/
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getAccount(),
                user.getPassword(),
                user.getIsActived(),
                mapToGrantedAuthorities(user.getRoles())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
