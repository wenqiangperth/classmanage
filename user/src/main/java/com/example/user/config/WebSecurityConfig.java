package com.example.user.config;

import com.example.common.config.FailureHandler;
import com.example.common.config.JwtUserDetailsService;
import com.example.common.config.MyAuthenticationSuccessHandler;
import com.example.common.config.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsUtils;

/**
 * @author perth
 * @ClassName WebSecurityConfig
 * @Description TODO
 * @Date 2018/12/18 22:42
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private FailureHandler failureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                // .loginPage("/user/login")
                .loginProcessingUrl("/user/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(failureHandler)
                //.permitAll()
                .and()
                .rememberMe()
                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()

                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
               // .antMatchers("/admin/*").hasRole("ADMIN")
                .antMatchers("/user/login").permitAll()
              //  .antMatchers("/user/*").permitAll()

                .antMatchers("/websocket/{klassseminarId}/{userId}/{role}").permitAll()
               // .antMatchers("/{klassseminarId}/team/{teamId}/enterseminar").permitAll()


                .antMatchers("/user/information").hasAnyRole("TEACHER","STUDENT")
                .antMatchers("/user/password").hasRole("STUDENT")
                .antMatchers("/user/email").hasRole("STUDENT")

                .antMatchers("/student/active").hasRole("STUDENT")


                .antMatchers("/course").hasAnyRole("STUDENT","TEACHER")
                .antMatchers("/course/{courseId}").hasAnyRole("STUDENT","TEACHER")
                .antMatchers("/course").hasAnyRole("TEACHER","STUDENT")
                .antMatchers("/course/{courseId}").hasAnyRole("TEACHER","STUDENT")
                .antMatchers("/course/{courseId}/class").hasRole("TEACHER")
                .antMatchers("/class/{classId}").hasRole("TEACHER")
                .antMatchers("/course/{courseId}/round").hasAnyRole("TEACHER","STUDENT")

                .antMatchers("/seminar/{seminarId}").hasAnyRole("TEACHER","STUDENT")
                .antMatchers("/course/{courseId}/round").hasAnyRole("TEACHER","STUDENT")
                .antMatchers("/seminar/{seminarId}").hasAnyRole("TEACHER","STUDENT")
                .antMatchers("/seminar").hasRole("TEACHER")
                .antMatchers("/seminar/{seminarId}/class/{classId}/attendance").hasAnyRole("TEACHER","STUDENT")
                .antMatchers("/seminar/{seminarId}/class/{classId}/report").hasAnyRole("TEACHER","STUDENT")
                .antMatchers("/seminar/{seminarId}/class/{classId}/seminarScore").hasRole("TEACHER")
                .antMatchers("/course/{courseId}/class/{classId}").hasRole("TEACHER")
               // .antMatchers("/user/*").permitAll()
               // .antMatchers("/user/information").hasRole("TEACHER")
                //.antMatchers("/user/password").hasRole("TEACHER")




                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()

                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//               // .loginPage("/user/login")
//                .loginProcessingUrl("/user/login")
//                .successHandler(myAuthenticationSuccessHandler)
//                .failureHandler(failureHandler)
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .csrf().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService)
                //密码使用BCryptPasswordEncoder()方法验证，因为这里使用了BCryptPasswordEncoder()方法验证。所以在注册用户的时候在接收前台明文密码之后也需要使用BCryptPasswordEncoder().encode(明文密码)方法加密密码。
                .passwordEncoder(new MyPasswordEncoder());

      /*  auth
                .inMemoryAuthentication()
                .withUser("22").password("per199822").roles("ADMIN");*/
    }
    // 装载BCrypt密码编码器
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
