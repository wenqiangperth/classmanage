package com.example.admin.config;

import com.example.common.dao.AdministratorDao;
import com.example.common.entity.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author perth
 * @ClassName JwtUserDetailsService
 * @Description TODO
 * @Date 2018/12/18 22:27
 * @Version 1.0
 **/
@Component
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private AdministratorDao userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrator user = userRepository.getAdministratorByAccount(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
