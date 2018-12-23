package com.example.common.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author perth
 * @ClassName MyPasswordEncoder
 * @Description 加密方式
 * @Date 2018/12/18 22:41
 * @Version 1.0
 **/
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}