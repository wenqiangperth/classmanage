package com.example.common.config;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author perth
 * @ClassName JwtUser
 * @Description TODO
 * @Date 2018/12/18 22:17
 * @Version 1.0
 **/
public class JwtUser implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final int isActive;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(
            Long id,
            String username,
            String password,
            int isActive,
            Collection<? extends GrantedAuthority> authorities) {
        System.out.println("init");
        this.id = id;
        this.username = username;
        this.password = password;
        this.isActive=isActive;
        this.authorities = authorities;
        System.out.println(authorities.toArray()[0]);
    }



    public Long getId() {
        return id;
    }

    public int getIsActive(){
        return isActive;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    /**
     * 返回分配给用户的角色
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否未过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否激活
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
